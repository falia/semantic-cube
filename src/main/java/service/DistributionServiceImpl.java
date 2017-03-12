package service;

import enumer.NS;
import model.Distribution;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.springframework.stereotype.Service;
import tripelstore.TripleStoreService;

import java.util.UUID;

@Service
public class DistributionServiceImpl implements DistributionService {

    @Override

    public Resource create(Distribution distribution) {
        Model model = TripleStoreService.getInstance().getModel();

        Resource r = model.createResource(NS.DISTRIBUTION.getUrl() + "/" + UUID.randomUUID());

        Resource distributionResource = model.getResource("http://www.w3.org/ns/dcat#Distribution");
        Property type = model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        r.addProperty(type, distributionResource);

        if(StringUtils.isNotEmpty(distribution.getDescription())) {
            Property title = model.createProperty("http://purl.org/dc/terms/description");
            r.addProperty(title, distribution.getDescription(), XSDDatatype.XSDstring);
        }

        if(StringUtils.isNotEmpty(distribution.getFormat())) {
            Property title = model.createProperty("http://purl.org/dc/terms/format");
            r.addProperty(title, distribution.getFormat(), XSDDatatype.XSDstring);
        }

        if(StringUtils.isNotEmpty(distribution.getDownloadURL())) {
            Property title = model.createProperty("http://purl.org/dc/terms/downloadURL");
            r.addProperty(title, distribution.getDownloadURL(), XSDDatatype.XSDstring);
        }

        return r;
    }


    public Distribution create2(Distribution distribution) {
        OntModel ontModel = TripleStoreService.getInstance().getOntModel();

        OntClass distributionClass = ontModel.getOntClass("http://www.w3.org/ns/dcat#Distribution");
        Individual instance = distributionClass.createIndividual(NS.DISTRIBUTION.getUrl()  + "/" +  UUID.randomUUID());

        if(StringUtils.isNotEmpty(distribution.getDescription())) {
            Property description = ontModel.getProperty("http://purl.org/dc/terms/description");
            instance.addProperty(description, distribution.getDescription());
        }

        if(StringUtils.isNotEmpty(distribution.getFormat())) {
            Property format = ontModel.getProperty("http://purl.org/dc/terms/format");
            instance.addProperty(format, distribution.getFormat());
        }

        if(StringUtils.isNotEmpty(distribution.getDownloadURL())) {
            Property downloadUrl = ontModel.getProperty("http://www.w3.org/ns/dcat#downloadURL");
            instance.addProperty(downloadUrl, distribution.getDownloadURL());
        }

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
