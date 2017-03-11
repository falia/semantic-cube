package tripelstore;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.tdb.TDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by borellda on 3/11/2017.
 */
public class TripleStoreService {

    private static final Logger log = LoggerFactory.getLogger(TripleStoreService.class);

    private static final String OWL_FILE = new String("./GoC_example.ttl");

    private Model model = RDFDataMgr.loadModel(OWL_FILE) ;

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

    public Model writeModelToFile(){
        Path newFile = Paths.get(OWL_FILE);
        try (OutputStream out = new FileOutputStream(newFile.toFile())) {
            model.write( out, "TTL" );
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return model;
    }
}
