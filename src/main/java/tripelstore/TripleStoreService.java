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
    /* The Logger */
    private static final Logger log = LoggerFactory.getLogger(TripleStoreService.class);
    private static final String TROPLESTORE = new String("./tbd");
    private Model dcatap;

    public TripleStoreService() {
        this.dcatap = RDFDataMgr.loadModel("./GoC_example.owl") ;
    }




}
