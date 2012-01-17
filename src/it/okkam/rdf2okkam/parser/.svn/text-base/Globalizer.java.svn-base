package it.okkam.rdf2okkam.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

/*
 * Statements of the input model that have a blank node as subject identifier
 * are updated with a URI in place of the blank node.The updated statements are 
 * saved in the output model. The output model cannot have blank nodes, only 
 * literals or URIs are admitted as values for predicates. 
 */

public class Globalizer {
	/*
	 * Returns the distinct subjects of all the statements in the rdf dataset (model).
	 * The subject resources are put in a set that cannot accept duplicates.
	 */
	public Set<org.openrdf.model.Resource> getDistinctSubjects(RepositoryConnection model) throws RepositoryException {
		Set<org.openrdf.model.Resource> subjects = new HashSet<org.openrdf.model.Resource>() ;
		RepositoryResult stmtIter = model.getStatements(null, null, null, true);
		while(stmtIter.hasNext()) {
		        Statement statment = (Statement) stmtIter.next();
	                Resource subject =statment.getSubject();			
			subjects.add((org.openrdf.model.Resource) subject) ;
		}		
		return subjects ;
	}
	
	/*
	 * Check whether the subject has already been recognized as an entity and stored
	 * in the output model 
	 */

}
