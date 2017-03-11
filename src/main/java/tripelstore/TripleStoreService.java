package tripelstore;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.tdb.TDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by borellda on 3/11/2017.
 */
public class TripleStoreService {

    private Model model = RDFDataMgr.loadModel("./GoC_example.owl") ;

    private static volatile  TripleStoreService INSTANCE = null;

    private TripleStoreService() {
    }

    public static TripleStoreService getInstance(){
        TripleStoreService result = INSTANCE;
        if (result == null) { // First check (no locking)
            synchronized(TripleStoreService.class) {
                result = INSTANCE;
                if (result == null) // Second check (with locking)
                    INSTANCE = result = new TripleStoreService();
            }
        }
        return result;
    }

    public Model getModel(){
        return this.model;
    }
}
