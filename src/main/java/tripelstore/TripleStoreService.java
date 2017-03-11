package tripelstore;

import org.apache.jena.query.Dataset;
import org.apache.jena.tdb.TDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by borellda on 3/11/2017.
 */
@Service
public class TripleStoreService {
    /* The Logger */
    private static final Logger log = LoggerFactory.getLogger(TripleStoreService.class);
    private static final String TROPLESTORE = new String("./tbd");

    private Dataset store;

    public TripleStoreService(){
        this.init();
    }

    public void init(){
         this.store = TDBFactory.createDataset(TROPLESTORE) ;
    }
}
