package it.okkam.rdf2okkam.ens;

import org.okkam.client.data.AttributesType;
import org.okkam.client.data.ProfileType;
import org.openrdf.model.Resource;



public interface EntityBuildStrategy {
	
	public ProfileType getProperties(Resource subjectNode) ;

}
