
package com.google.refine.ENSUploader.rdf.uploaders;

import it.okkam.rdf2okkam.controller.ApplicationController;
import it.okkam.rdf2okkam.parser.Globalizer;

import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openrdf.model.BNode;
import org.openrdf.model.Resource;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.sail.memory.MemoryStore;

import com.google.refine.browsing.Engine;
import com.google.refine.browsing.FilteredRows;
import com.google.refine.browsing.RowVisitor;
import com.google.refine.model.Project;
import com.google.refine.model.Row;
import com.google.refine.rdf.Node;
import com.google.refine.rdf.RdfSchema;
import com.google.refine.rdf.Util;
import com.google.refine.rdf.app.ApplicationContext;
import com.google.refine.rdf.vocab.VocabularyIndexException;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryResult;

public class RdfUploader {

    static ApplicationController controller = null;
    Globalizer global = null;
   // private static Log log = LogFactory.getLog(RdfUploader.class);
    private RDFFormat format;
    private ApplicationContext applicationContext;
    public int countSubjs = 0;
    private static Logger logger = Logger.getLogger(RdfUploader.class);
    
    public RdfUploader(ApplicationContext ctxt, RDFFormat f) {
        this.format = f;
        this.applicationContext = ctxt;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void upload(Project project, Engine engine)
            throws IOException {
        RdfSchema schema;
        try {
            schema = Util.getProjectSchema(applicationContext, project);
        } catch (VocabularyIndexException ve) {
            throw new IOException("Unable to create index for RDF schema", ve);
        }
        Repository model = buildModel(project, engine, schema);

        try {
            RepositoryConnection con = model.getConnection();
            
            Set<Resource> distSubjs = getDistinctSubjects(con);
            con.close();
            
            con = model.getConnection();
            Iterator<Resource> idistSubj = distSubjs.iterator();
          
            controller = new ApplicationController(con);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
                
            logger.info("======================================================================================");
            logger.info(dateFormat.format(date));
            logger.info("Entities created  for project id = " + project.id);
            while (idistSubj.hasNext()) {
                Resource distSubj = idistSubj.next();
                String okkamid = controller.createEntity(distSubj);
                countSubjs++;
                logger.info(okkamid);
            }
            
            logger.info(countSubjs+ " Entities Created" );
            logger.info("======================================================================================");
            con.close();

        } catch (RepositoryException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Repository buildModel(final Project project, Engine engine, RdfSchema schema)
            throws IOException {
        RdfRowVisitor visitor = new RdfRowVisitor(schema) {

            @Override
            public boolean visit(Project project, int rowIndex, Row row) {
                for (Node root : roots) {
                    root.createNode(baseUri, factory, con, project, row, rowIndex, blanks);
                }
                return false;
            }
        };
        Repository model = buildModel(project, engine, visitor);

        return model;
    }

    public static Repository buildModel(Project project, Engine engine, RdfRowVisitor visitor) {
        // RdfSchema schema = Util.getProjectSchema(project);
        FilteredRows filteredRows = engine.getAllFilteredRows();
        filteredRows.accept(project, visitor);
        return visitor.getModel();

    }

    public String getContentType() {
        if (format.equals(RDFFormat.TURTLE)) {
            return "text/turtle";
        } else {
            return "application/rdf+xml";
        }
    }

    public boolean takeWriter() {
        return true;
    }

    public Set<org.openrdf.model.Resource> getDistinctSubjects(RepositoryConnection model)
            throws RepositoryException {
        Set<org.openrdf.model.Resource> subjects = new HashSet<org.openrdf.model.Resource>();
        RepositoryResult stmtIter = model.getStatements(null, null, null, true);
        while (stmtIter.hasNext()) {
            org.openrdf.model.Statement statment = (org.openrdf.model.Statement) stmtIter.next();
            Resource subject = statment.getSubject();
            subjects.add((org.openrdf.model.Resource) subject);
        }
        return subjects;

    }

    public static abstract class RdfRowVisitor implements RowVisitor {

        protected Repository model;
        protected URI baseUri;
        protected BNode[] blanks;
        protected List<Node> roots;
        private RdfSchema schema;

        protected ValueFactory factory;
        protected RepositoryConnection con;

        public Repository getModel() {
            return model;
        }

        public RdfRowVisitor(RdfSchema schema) {
            this.schema = schema;
            baseUri = schema.getBaseUri();
            roots = schema.getRoots();

            // initilaizing repository
            model = new SailRepository(new MemoryStore());
            try {
                model.initialize();
                RepositoryConnection con = model.getConnection();
                try {
                    ValueFactory factory = con.getValueFactory();
                    blanks = new BNode[schema.get_blanks().size()];
                    for (int i = 0; i < blanks.length; i++) {
                        blanks[i] = factory.createBNode();
                    }
                } finally {
                    con.close();
                }
            } catch (RepositoryException ex) {
                throw new RuntimeException(ex);
            }
        }

        public void end(Project project) {
            try {
                if (con.isOpen()) {
                    con.close();
                }
            } catch (RepositoryException e) {
                throw new RuntimeException("", e);
            }
        }

        public void start(Project project) {
            try {
                con = model.getConnection();
                factory = con.getValueFactory();
            } catch (RepositoryException ex) {
                throw new RuntimeException("", ex);
            }
        }

        abstract public boolean visit(Project project, int rowIndex, Row row);

        public RdfSchema getRdfSchema() {
            return schema;
        }
    }

}
