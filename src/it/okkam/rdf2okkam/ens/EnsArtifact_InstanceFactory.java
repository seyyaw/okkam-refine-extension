package it.okkam.rdf2okkam.ens;

import org.openrdf.model.Resource;
import org.openrdf.repository.RepositoryConnection;



public class EnsArtifact_InstanceFactory extends EnsEntityFactory {

	
	public EnsEntity createEnsEntity(RepositoryConnection model, Resource node) {
 
		return (EnsEntity) new EnsArtifact_Instance( model, node ) ;
		
	}

}
