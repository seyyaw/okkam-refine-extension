package it.okkam.rdf2okkam.controller;

import java.io.File;

import it.okkam.rdf2okkam.ens.EnsEntity;
import it.okkam.rdf2okkam.ens.EnsEntityFactory;
import it.okkam.rdf2okkam.ens.EntityTypeSelector;
import it.okkam.rdf2okkam.ens.client.OkkamClient;
import it.okkam.rdf2okkam.ens.client.ServiceClient;
import org.openrdf.model.Resource;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class ApplicationController {

    
        
        File file = new File("extensions/ENSUploader/conf"); 
	private final String confpath = file.getAbsolutePath();;
	
	RepositoryConnection _model = null ;
		
	EnsEntityFactory factory = null;
	
	OkkamClient okkam = null;
	
	ServiceClient service = null ;
	
	
	EntityTypeSelector selector = null;
	
	public ApplicationController(RepositoryConnection model) {
		this._model=model;
		okkam = new OkkamClient( confpath ) ;
		service = new ServiceClient( okkam ) ;
		selector = new EntityTypeSelector(_model) ;
	}
	
	
	public String createEntity(Resource node) throws RepositoryException {
		
		String result = "" ; // okkamid
		
		factory = selector.selectEntityFactory(node);
		
		EnsEntity entity = factory.createEnsEntity(_model, node);
				
		boolean ignoreDuplicates = true ; //no duplication or allow
		
		
		result = service.createNewEntity(entity.getSemanticType(), 
				entity.getAttributesType(), ignoreDuplicates) ;
		
		return result ;
	}

}
