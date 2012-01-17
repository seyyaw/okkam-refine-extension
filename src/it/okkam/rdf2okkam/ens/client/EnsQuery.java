package it.okkam.rdf2okkam.ens.client;

import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.okkam.client.data.AttributeType;
import org.okkam.client.data.AttributesType;



public class EnsQuery {
	
	private static String QUERY_PREFIX = "QUERY { " ;
	private static String QUERY_SUFFIX = " } " ;
	private static String METADATA_PREFIX = "METADATA { " ;
	private static String METADATA_SUFFIX = " }" ;
	
	/*
	 * Build up the query string to be sent to the ENS from the attributes and 
	 * the semantic type of the entity profile.
	 */
	public static String getQuery(AttributesType attrsType, String semanticType) {
		String result =  QUERY_PREFIX ;
		
		List<AttributeType> attrTypes = attrsType.getAttributes() ;
		Iterator<AttributeType> iattrType = attrTypes.iterator() ; 
		while(iattrType.hasNext()) {
			AttributeType attrType = iattrType.next() ;
			QName qname = attrType.getName() ;
			String name = qname.getLocalPart() ;
			String value = attrType.getValue() ;
			String nameValuePair = "\"" + name + "\"=" + "\"" + value + "\" " ;
			result += nameValuePair ;
		}
		
		result += QUERY_SUFFIX ;
		
		result += METADATA_PREFIX ;
		
		result += "entityType=\"" + semanticType + "\"" + METADATA_SUFFIX ;
		
		return result ;
	}
	
	public static String getQuery(List<String> attributes, String semanticType) {
		String result =  QUERY_PREFIX ;
		
		Iterator<String> iattribute = attributes.iterator() ; 
		while(iattribute.hasNext()) {
			String attribute = iattribute.next() ;
			result += attribute + " " ;
			
		}
		
		result += QUERY_SUFFIX ;
		
		result += METADATA_PREFIX ;
		
		result += "entityType=\"" + semanticType + "\"" + METADATA_SUFFIX ;
		
		return result ;
	}

}
