package tripelstore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by borellda on 3/11/2017.
 */
public class TripleStoreService {

	private static final Logger log = LoggerFactory.getLogger(TripleStoreService.class);

	private static final String OWL_FILE = new String("/GoC_example.ttl");

	private Model model = RDFDataMgr.loadModel(OWL_FILE);

	private static volatile TripleStoreService INSTANCE = null;

	private OntModel ontModel;

	private TripleStoreService() {
	}

	public static TripleStoreService getInstance() {
		TripleStoreService result = INSTANCE;
		if (result == null) { // First check (no locking)
			synchronized (TripleStoreService.class) {
				result = INSTANCE;
				if (result == null) // Second check (with locking)
					INSTANCE = result = new TripleStoreService();
			}
		}
		return result;
	}

	public Model getModel() {
		URL location = TripleStoreService.class.getProtectionDomain().getCodeSource().getLocation();
		log.info(location.getFile());
		log.info(Paths.get(OWL_FILE).toAbsolutePath().toString());
		return this.model;
	}

	public OntModel getOntModel() {
		if (ontModel == null) {
			ontModel = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM, model);
		}

		return ontModel;
	}

	public synchronized Model writeModelToFile() {
		Path newFile = Paths.get(OWL_FILE);
		log.info(newFile.toAbsolutePath().toString());
		try (OutputStream out = new FileOutputStream(newFile.toFile())) {
			model.write(out, "TTL");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return model;
	}

	public Model writeOntModelToFile() {
		Path newFile = Paths.get(OWL_FILE);
		try (OutputStream out = new FileOutputStream(newFile.toFile())) {
			model.write(out, "TTL");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return model;
	}
}
