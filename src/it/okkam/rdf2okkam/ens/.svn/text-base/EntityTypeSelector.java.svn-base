
package it.okkam.rdf2okkam.ens;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openrdf.model.Resource;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

import it.okkam.rdf2okkam.parser.VocabConstants;


public class EntityTypeSelector {

    EnsEntityFactory factory = null;

    RepositoryConnection _model = null;

    private static Log log = LogFactory.getLog(EntityTypeSelector.class);

    public EntityTypeSelector(RepositoryConnection model) {
        _model = model;
    }

    public EnsEntityFactory selectEntityFactory(Resource node)
            throws RepositoryException {

        String entityType = getEntityType(node);

        if (VocabConstants.ENS_PERSON.equals(entityType)) {
            factory = new EnsPersonFactory();
        }

        if (VocabConstants.ENS_LOCATION.equals(entityType)) {
            factory = new EnsLocationFactory();
        }

        if (VocabConstants.ENS_EVENT.equals(entityType)) {
            factory = new EnsEventFactory();
        }

        if (VocabConstants.ENS_ARTIFACTTYPE.equals(entityType)) {
            factory = new EnsArtifact_TypeFactory();
        }

        if (VocabConstants.ENS_OTHER.equals(entityType)) {
            factory = new EnsOtherFactory();
        }

        return factory;

    }

    private String getEntityType(Resource subjectNode)
            throws RepositoryException {
        String result = null;
        RepositoryResult istmt = _model.getStatements(subjectNode, null, null, true);
        while (istmt.hasNext()) {
            org.openrdf.model.Statement stmt = (org.openrdf.model.Statement) istmt.next();
            String predicate = stmt.getPredicate().getLocalName();

            if (predicate.equals("type")) {
                result =  stmt.getObject().stringValue();
                break;
            }
        }

        return result;
    }

}
