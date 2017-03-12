package com.arhscube.gameofcode;

import config.SpringBootWebApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.SparqlService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringBootWebApplication.class)
public class SearchTest {

    @Autowired
    private SparqlService sparqlService;

    public void test() throws Exception {

    }

}
