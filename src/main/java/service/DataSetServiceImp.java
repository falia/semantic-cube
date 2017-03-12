package service;

import enumer.NS;
import model.DataSet;
import model.Distribution;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tripelstore.TripleStoreService;

import java.util.UUID;

@Service
public class DataSetServiceImp implements DataSetService {

    @Autowired
    private DistributionService distributionService;

    @Override
    public DataSet create(DataSet dataSet) {
        OntModel ontModel = TripleStoreService.getInstance().getOntModel();

        OntClass dataSetClass = ontModel.getOntClass("http://www.w3.org/ns/dcat#Dataset");
        Individual instance = dataSetClass.createIndividual(NS.DATA_SET.getUrl() + "/" + UUID.randomUUID());

        Property title = ontModel.getProperty("http://purl.org/dc/terms/title");
        instance.addProperty(title, dataSet.getTitle());

        Property description = ontModel.getProperty("http://purl.org/dc/terms/description");
        instance.addProperty(description, dataSet.getDescription());

        for(String euroVoc : dataSet.getEurovocUris()) {
            Individual euroVocConcept = ontModel.getIndividual("http://eurovoc.europa.eu/" + euroVoc);
            if(euroVocConcept != null) {
                Property theme = ontModel.getProperty("http://www.w3.org/ns/dcat#theme");
                instance.addProperty(theme, euroVocConcept);
            } else {
                System.out.println("eurovoc is empty");
            }
        }

        for(Distribution distribution : dataSet.getDistributionList()) {
            distributionService.create(distribution);
        }

        System.out.println("faton");

        return dataSet;
    }

    @Override
    public DataSet update(DataSet dataSet) {
        return null;
    }

    @Override
    public void delete(DataSet dataSet) {

    }

    @Override
    public DataSet find(DataSet dataSet) {
        return null;
    }
}
