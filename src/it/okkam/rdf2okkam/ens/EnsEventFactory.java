package it.okkam.rdf2okkam.ens;

import org.openrdf.model.Resource;
import org.openrdf.repository.RepositoryConnection;



public class EnsEventFactory extends EnsEntityFactory {

	
	public EnsEntity createEnsEntity(RepositoryConnection model, Resource node) {
 
		return (EnsEntity) new EnsEvent( model, node ) ;
		
	}

}
