package com.arhscube.gameofcode;

import org.apache.jena.rdf.model.Model;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tripelstore.TripleStoreService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by borellda on 3/11/2017.
 */
public class CreateLocalripleStoreTestT {
    /* The Logger */
    private static final Logger log = LoggerFactory.getLogger(CreateLocalripleStoreTestT.class);


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
}


