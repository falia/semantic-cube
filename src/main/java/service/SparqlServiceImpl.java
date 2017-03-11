package service;

import org.apache.jena.graph.Graph;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tripelstore.TripleStoreService;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by borellda on 3/11/2017.
 */
@Service
public class SparqlServiceImpl implements SparqlService{
    /* The Logger */
    private static final Logger log = LoggerFactory.getLogger(SparqlServiceImpl.class);

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
    public void writeSparqlQuery(String query) {

    }

    public void addRdf2Model(String rdfRepresentation){
        InputStream is = new ByteArrayInputStream( rdfRepresentation.getBytes() );
        this.addRdf2Model(is);
    }

    public void addRdf2Model(Path rdfFilePath){
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
        store.union(memModel);
    }
}
