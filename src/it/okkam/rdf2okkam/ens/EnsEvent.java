package it.okkam.rdf2okkam.ens;

import it.okkam.rdf2okkam.ens.client.EnsQuery;
import it.okkam.rdf2okkam.parser.VocabConstants;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.okkam.client.data.AttributeType;
import org.okkam.client.data.AttributesType;
import org.okkam.client.data.ProfileType;
import org.okkam.core.data.api.SemanticType;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;


public class EnsEvent implements EnsEntity {

	private RepositoryConnection _model ;
	
	private org.openrdf.model.Resource _subjectNode ;
	
	private static Log log = LogFactory.getLog(EnsEvent.class);
	
	public EnsEvent(RepositoryConnection model, org.openrdf.model.Resource node){
		
		_model = model ;
		_subjectNode = node ;
	}
	
	@Override
	public String getSemanticType() {
		
		return SemanticType.EVENT ;
	}
	
	 /*
         * Returns person's list of attributes from the model
         */
        public AttributesType getAttributesType()    throws RepositoryException {
            AttributesType attributes = new AttributesType();
            RepositoryResult istmt = _model.getStatements(_subjectNode, null, null, true);
            while (istmt.hasNext()) {
                String prefix = "";
                org.openrdf.model.Statement stmt = (org.openrdf.model.Statement) istmt.next();
                AttributeType attribute = new AttributeType();
                org.openrdf.model.Resource predicate = stmt.getPredicate();

                // set the prefix
                if (VocabConstants.ensNS.equals(predicate.stringValue())) { // getLocalName
                    prefix = VocabConstants.ENS_PREFIX;
                }
                if (VocabConstants.taxNS.equals(predicate.stringValue())) {
                    prefix = VocabConstants.TAX_PREFIX;
                }
                if (VocabConstants.rdfNS.equals(predicate.stringValue())) {
                    prefix = VocabConstants.RDF_PREFIX;
                }

                // select only predicates used as entity attributes
                String pred = stmt.getPredicate().getLocalName();
                if (isAttribute(pred)) {
                    // QName name = new QName(predicate.getURI(),
                    // predicate.getLocalName() , prefix);

                    if (prefix.equals("")) { // if the attribute was not one of the
                                             // ontology term
                        pred = pred.replace("+", "_");
                    }
                    QName name = new QName("", pred, prefix);
                    attribute.setName(name);
                    String value = stmt.getObject().toString();
                    // System.out.println("value: " + value) ;
                    attribute.setValue(value);

                    // Set access control metadata to send or not attributes values
                    // to the public node
                    // Set to "private" to not send the attributes values.
                    // attribute.getMetadata().getAccessControl().setDisplayable("private");

                    attributes.getAttributes().add(attribute);
                }

            }

            return attributes;
        }

        /*
         * Returns person's profile from the model
         */
        @Override
        public ProfileType getProfile()
                throws RepositoryException {
            ProfileType profile = new ProfileType();

            profile.setAttributes(getAttributesType());
            profile.setSemanticType(getSemanticType());

            return profile;
        }

        public String getQuery()
                throws RepositoryException {

            return EnsQuery.getQuery(getAttributesType(), getSemanticType());
        }

        /*
         * Select predicates that are entity's attribute. If a predicate is not an
         * entity attribute the method returns false.
         */

        private boolean isAttribute(String predicate) {
            boolean result = true;

            if (predicate.equals("type")) result = false;

            return result;
        }

    }
