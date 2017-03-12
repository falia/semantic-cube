package service;

import enumer.NS;
import model.Distribution;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.springframework.stereotype.Service;
import tripelstore.TripleStoreService;

import java.util.UUID;

@Service
public class DistributionServiceImpl implements DistributionService {

    @Override
    public Distribution create(Distribution distribution) {
        OntModel ontModel = TripleStoreService.getInstance().getOntModel();

        OntClass distributionClass = ontModel.getOntClass("http://www.w3.org/ns/dcat#Distribution");
        Individual instance = distributionClass.createIndividual(NS.DISTRIBUTION.getUrl()  + "/" +  UUID.randomUUID());

        Property description = ontModel.getProperty("http://purl.org/dc/terms/description");
        instance.addProperty(description, distribution.getDescription());

        Property format = ontModel.getProperty("http://purl.org/dc/terms/format");
        instance.addProperty(format, distribution.getFormat());

        Property downloadUrl = ontModel.getProperty("http://www.w3.org/ns/dcat#downloadURL");
        instance.addProperty(downloadUrl, distribution.getFormat());

        return null;
    }

    @Override
    public Distribution update(Distribution distribution) {
        return null;
    }

    @Override
    public void delete(Distribution distribution) {

    }

    @Override
    public Distribution find(Distribution distribution) {
        return null;
    }
}
