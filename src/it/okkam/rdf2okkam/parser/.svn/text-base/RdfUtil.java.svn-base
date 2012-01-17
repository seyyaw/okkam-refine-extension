
package it.okkam.rdf2okkam.parser;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;


public class RdfUtil {

    private final String ensOntology = "resources/vocabulary/ENS-core-vocabulary.owl";

    private static Log log = LogFactory.getLog(RdfUtil.class);

    public Set<org.openrdf.model.Resource> getDistinctSubjects(RepositoryConnection con)
            throws RepositoryException {
        Set<org.openrdf.model.Resource> subjects = new HashSet<org.openrdf.model.Resource>();
        RepositoryResult stmtIter = con.getStatements(null, null, null, true);
        while (stmtIter.hasNext()) {
            org.openrdf.model.Statement statment = (org.openrdf.model.Statement) stmtIter.next();
            subjects.add(statment.getSubject());
        }

        return subjects;
    }
}
