package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by borellda on 3/11/2017.
 */
public interface SparqlService {

    void readSparqlQuery(String query);

    void writeSparqlQuery(String query);

    default void flushSparqlQuery(String query){

    }


}
