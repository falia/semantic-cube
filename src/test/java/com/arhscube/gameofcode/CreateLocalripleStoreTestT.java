package com.arhscube.gameofcode;

import org.apache.jena.rdf.model.Model;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SparqlService;
import service.SparqlServiceImpl;
import tripelstore.TripleStoreService;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by borellda on 3/11/2017.
 */
public class CreateLocalripleStoreTestT {
    /* The Logger */
    private static final Logger log = LoggerFactory.getLogger(CreateLocalripleStoreTestT.class);

    private static final String TESTRDF = new String("<?xml version=\"1.0\"?>\n" +
            "\n" +
            "<rdf:RDF\n" +
            "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
            "xmlns:si=\"https://www.w3schools.com/rdf/\">\n" +
            "\n" +
            "<rdf:Description rdf:about=\"https://www.w3schools.com\">\n" +
            "  <si:title>W3Schools</si:title>\n" +
            "  <si:author>Jan Egil Refsnes</si:author>\n" +
            "</rdf:Description>\n" +
            "\n" +
            "</rdf:RDF>");



    @Test
    public void createModelTest(){
        Model store =  TripleStoreService.getInstance().getModel();
        assertNotNull(store);
    }


    @Test
    public void singleModelInstanceTest(){
        Model store =  TripleStoreService.getInstance().getModel();
        Model store2 =  TripleStoreService.getInstance().getModel();
        assertEquals(store, store2);
    }

    @Test
    public void writeModel2FileTest(){
        Model store =  TripleStoreService.getInstance().getModel();
        Model store2 = TripleStoreService.getInstance().writeModelToFile();
        assertEquals(store, store2);
    }

    @Test
    public void updateModelWithRdfTest(){
        Model store =  TripleStoreService.getInstance().getModel();
        long size1 = store.size();
        SparqlService service = new SparqlServiceImpl();
        service.addRdf2Model(TESTRDF);
        Model store2 = TripleStoreService.getInstance().getModel();
        long size2 = store2.size();
        assertNotEquals(size1, size2);
    }

    @Test
    public void loadEuroVocRdfTest(){
        Model store =  TripleStoreService.getInstance().getModel();
        SparqlService service = new SparqlServiceImpl();
        long size1 = store.size();
        service.addRdf2Model(Paths.get(new String("./target/test-classes/eurovoc_in_skos_core_concepts.rdf")));
        long size2 = store.size();
        service.flushSparqlQuery();
        assertNotEquals(size1, size2);
    }


}


