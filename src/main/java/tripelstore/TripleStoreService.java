package tripelstore;

import org.apache.jena.query.Dataset;
import org.apache.jena.tdb.TDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by borellda on 3/11/2017.
 */
@Component
public class TripleStoreService {
    /* The Logger */
    private static final Logger log = LoggerFactory.getLogger(TripleStoreService.class);
    private static final String TROPLESTORE = new String("./tbd");

    private Dataset dataset;

    public void init(){
         this.dataset = TDBFactory.createDataset(TROPLESTORE) ;
    }
}
