package service;

import org.apache.jena.graph.Graph;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import tripelstore.TripleStoreService;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

/**
 * Created by borellda on 3/11/2017.
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SparqlServiceImpl implements SparqlService{
    /* The Logger */
    private static final Logger log = LoggerFactory.getLogger(SparqlServiceImpl.class);

    @Override
    public boolean isQueryValid(String queryString) {
        try {
            Query query = QueryFactory.create(queryString) ;
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public ResultSet readSparqlQuery(String queryString) {
        Model store =  TripleStoreService.getInstance().getModel();
        Query query = QueryFactory.create(queryString) ;
        ResultSet results = null;
        try (QueryExecution qexec = QueryExecutionFactory.create(query, store)) {
            results = qexec.execSelect();
            results = ResultSetFactory.copyResults(results) ;
        }
        catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return results;
    }

    @Override
    public void addStatements2Store(Collection<Statement> statementList) {
        Model store =  TripleStoreService.getInstance().getModel();
        store.add(statementList.toArray(new Statement[statementList.size()]));
    }
    @Override
    public void addRdf2Model(String rdfRepresentation){
        InputStream is = new ByteArrayInputStream( rdfRepresentation.getBytes() );
        this.addRdf2Model(is);
    }
    @Override
    public void addRdf2Model(Path rdfFilePath){
        log.info(rdfFilePath.toAbsolutePath().toString());
        File file = rdfFilePath.toFile();
        try (InputStream in = new FileInputStream(rdfFilePath.toFile())) {
            this.addRdf2Model(in);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }



    private void addRdf2Model(InputStream in){
        Model memModel = ModelFactory.createDefaultModel();
        Model store =  TripleStoreService.getInstance().getModel();
        memModel.read(in, null);
        StmtIterator iter = memModel.listStatements();
        while (iter.hasNext()){
            store.add(iter.nextStatement());
        }
    }
}
