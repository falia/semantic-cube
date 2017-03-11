package service;

import org.apache.jena.query.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tripelstore.TripleStoreService;

/**
 * Created by borellda on 3/11/2017.
 */
public interface SparqlService {

    ResultSet readSparqlQuery(String queryString);

    void writeSparqlQuery(String queryString);

    default void flushSparqlQuery(){
        TripleStoreService.getInstance().getModel();
    }


}
