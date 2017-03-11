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
public enum TripleStoreService {

    STORE {
        @Override
        public Model getModel(){
            return RDFDataMgr.loadModel("./GoC_example.owl") ;
        }
    };

    public abstract Model getModel();
}
