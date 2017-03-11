package service;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tripelstore.TripleStoreService;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

/**
 * Created by borellda on 3/11/2017.
 */
public interface SparqlService {

    ResultSet readSparqlQuery(String queryString);

    void addStatements2Store(Collection<Statement> statementList);

    void addRdf2Model(String rdfRepresentation);

    void addRdf2Model(Path rdfFilePath);

    default void flushSparqlQuery(){
        TripleStoreService.getInstance().writeModelToFile();
    }


}
