package it.okkam.rdf2okkam.ens.client;

import static org.junit.Assert.assertNotNull;

import it.okkam.rdf2okkam.ens.client.CandidateEntity;
import it.okkam.rdf2okkam.ens.client.QueryResponse;
import it.okkam.rdf2okkam.ens.client.ResponseType;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;

import org.okkam.client.data.AlternativeIdsType;
import org.okkam.client.data.AttributeMetadataType;
import org.okkam.client.data.AttributeType;
import org.okkam.client.data.AttributesType;
import org.okkam.client.data.Entity;
import org.okkam.client.data.EquivalentOidsType;
import org.okkam.client.data.ProfileType;
import org.okkam.client.data.ReferencesType;
import org.okkam.core.ws.data.EntityValidationReport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class ServiceClient {
	
	private OkkamClient _okkamClient;
	private static Log log = LogFactory.getLog(ServiceClient.class);
	

	
	public ServiceClient(OkkamClient okkamclient){		

		log.info("Starting service client.");

		_okkamClient = okkamclient;
		
		
//		entityTypes = new ArrayList<String>();
//		entityTypes.add(SemanticType.ARTIFACT_INSTANCE);
//		entityTypes.add(SemanticType.ARTIFACT_TYPE);
//		entityTypes.add(SemanticType.EVENT);
//		entityTypes.add(SemanticType.LOCATION);
//		entityTypes.add(SemanticType.ORGANIZATION);
//		entityTypes.add(SemanticType.OTHER);
//		entityTypes.add(SemanticType.PERSON);
		
	}
	
	
	/*
	 * An entity has
	 * - a profile
	 * - equivalent ids
	 * - alternative ids
	 * A profile has
	 * - semantic type
	 * - references
	 * - a list of attributes
	 * 
	 * Creates a new entity in the ENS and returns the identifier. The boolean argument enable the client 
	 * to create a new entity also in presence of entities that are considered the same by the ENS (duplicates).  
	 */
	public String createNewEntity(String semanticType, AttributesType attributeList, boolean ignoreDuplicates) {
		
		log.debug("Creating a new entity");
		
		//THE ENTITY OBJECT IS THE WRAPPER OF ALL THE ENTITY'S INFORMATION
		Entity entity = new Entity();
		
		// CREATE AN ALTERNATIVE IDS SET (IF ANY)
		AlternativeIdsType alts = new AlternativeIdsType();
		
		// CREATE AN EQUIVALENT IDS SET (IF ANY)   
		EquivalentOidsType eq = new EquivalentOidsType();
		
		// ENTITY PROFILE CONTAINS MOST OF THE ENTITY DESCRIPTION
		ProfileType profile = new ProfileType();
		
		// CREATE AN EXTERNAL REFERENCE SET (IF ANY)
		ReferencesType refs = new ReferencesType();  
		
		
		if (attributeList.getAttributes().size() > 0) {

		    log.debug("Adding attributes");

		    profile.setAttributes(attributeList);

		}
		 
		// SET THE SEMANTIC TYPE OF THE ENTITY 	
		if ( semanticType != null && ! "".equals(semanticType) ) {

		    log.debug("Adding semantic type: " + semanticType);

		    profile.setSemanticType(semanticType);

		}
		
		if (refs.getReferences().size() > 0) {

		    log.debug("Adding references");

		    profile.setReferences(refs);

		}

		 

		if (alts.getAlternativeIds().size() > 0) {

		    log.debug("Adding alternatives");

		    entity.setAlternativeIds(alts);

		}

		 

		if (eq != null && eq.getEquivalentOids().size() > 0) {

		    log.debug("Adding equivalents");

		    entity.setEquivalentOids(eq);

		}

		// ADD PROFILE TO ENTITY OBJECT

		entity.setProfile(profile);
		
		// ENTITY VALIDATION
		EntityValidationReport report = null;
		
		try {
			
			 report = _okkamClient.validateEntity(entity, ignoreDuplicates);
			 
		}
		catch(Throwable t){
		
			t.printStackTrace();
			log.error("Cannot create a new entity. ");
			
		}
		
		assertNotNull(report);
		
		// IF THE VALIDATION WAS POSITIVE, A VALIDATION CERTIFICATE IS RETURNED

		String certificate = report.getCertificate();
		
		log.debug("Certificate: " + certificate);
		
		String okkamId = "";

		if(certificate != null){

		    // IF THE CERTIFICATE IS NOT NULL, THE VALIDATION IS POSITIVE AND THE ENTITY CAN BE CREATED

		     okkamId = _okkamClient.createNewEntity(certificate);
		     
		     log.info("New entity: " + okkamId);
		    

		} 
		else {

		    log.debug("See validateEntity for more information about how to manage entity validation report.");

		}
		
		return okkamId;
	}
	
	/*
	 * Search for an entity by its query string
	 */
	public List<QueryResponse> findEntity(String query) {
		
		ArrayList<QueryResponse> candidates = new ArrayList<QueryResponse>();
		
		ArrayList<CandidateEntity> entityList = _okkamClient.inquireOkkam(query);
		Iterator<CandidateEntity> entityIterator = entityList.iterator();
		
		
		while(entityIterator.hasNext()){
			QueryResponse result = new QueryResponse();
			CandidateEntity candidateEntity = entityIterator.next();
			Entity entity = candidateEntity.getEntity();
			boolean match = candidateEntity.isMatch();
			String okkamId = entity.getOid();
			result.setId(okkamId);			
			ProfileType profile = entity.getProfile();			
			String semanticType = profile.getSemanticType();
			if(semanticType != null & !semanticType.equals("")){
				ArrayList types = new ArrayList();
				ResponseType type = new ResponseType();
				type.setId(semanticType);
				type.setName(semanticType);
				types.add(type);
				result.setType(types);
			}
			
			AttributesType attributesType = profile.getAttributes();
			List<AttributeType> attributeTypeList = attributesType.getAttributes();
			Iterator<AttributeType> attributeTypeIterator = attributeTypeList.iterator();
			while(attributeTypeIterator.hasNext()){
				AttributeType attributeType = attributeTypeIterator.next();
				attributeType.getName().getNamespaceURI();
				attributeType.getName().getLocalPart();
				String value = attributeType.getValue();
				//System.out.println("Nome attributo: " + nameSpaceUri + "#" + term + ", valore: " + value);
				AttributeMetadataType metadata = attributeType.getMetadata();
				metadata.getProvenance().getSource();				
				//System.out.println("Fonte dati: " + dataSource);												
				if(query.trim().toLowerCase().contains( value.trim().toLowerCase() )){
					result.setName(value);					
					result.setScore(99.99);
					result.setMatch(match);
				}								
			}
			
			candidates.add(result);
		}
		
		return candidates;
	}
	
	public Map<String, String> findEntityByOkkamId(String okkamid){
		HashMap<String, String> result = new HashMap<String, String>();
		String semanticType = "";
		Entity entity = _okkamClient.getEntity(okkamid);
		ProfileType profile = entity.getProfile();
		semanticType = profile.getSemanticType();
		
		AttributesType attributes = profile.getAttributes();
		List<AttributeType> attributeList = attributes.getAttributes();
		
		result.put("semanticType", semanticType);
		Iterator<AttributeType> i = attributeList.iterator();
		while(i.hasNext()){
			AttributeType attribute = i.next();
			QName q = attribute.getName();
			String attrName = q.getLocalPart();
			String attrValue = attribute.getValue();
			result.put(attrName, attrValue);
		}
		
		return result;
		
		
	}
	
	public void deleteEntity(String okkamId) {
		
		
		String ticket = null ;
		try {
			
			ticket = _okkamClient.lockEntity(okkamId);			
			
			_okkamClient.deleteEntity( okkamId, ticket );
			
		} 
		catch (Throwable t) {
		
			log.info(ticket + " not locked") ;
			
		}
		
	}
	
	public void deleteEntityByQuery(String query) {

		new ArrayList<QueryResponse>();

		ArrayList<CandidateEntity> entityList = _okkamClient
				.inquireOkkam(query);
		Iterator<CandidateEntity> entityIterator = entityList.iterator();

		while (entityIterator.hasNext()) {
			new QueryResponse();
			CandidateEntity candidateEntity = entityIterator.next();
			Entity entity = candidateEntity.getEntity();
			candidateEntity.isMatch();
			String okkamId = entity.getOid();

			String ticket = null;
			try {

				ticket = _okkamClient.lockEntity(okkamId);

				_okkamClient.deleteEntity(okkamId, ticket);

			} catch (Throwable t) {

				log.info(ticket + " not locked");

			}
		}
	}
	public void deleteEntityFromFile(String filePath){
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			
			String oid;
			while((oid = br.readLine())!=null) {
				String ticket = _okkamClient.lockEntity(oid);
				_okkamClient.deleteEntity(oid, ticket);
				_okkamClient.unlockEntity(oid, ticket);
				log.info("Deleted entity: " + oid);
			}
			
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}

	}
	
	

}
