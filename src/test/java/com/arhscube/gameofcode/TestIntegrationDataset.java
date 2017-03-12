package com.arhscube.gameofcode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import config.SpringBootWebApplication;
import org.junit.Test;

import com.arhscube.gameofcode.datapublic.Crawler;
import com.arhscube.gameofcode.datapublic.OpenDataset;
import com.arhscube.gameofcode.datapublic.Source;
import com.arhscube.gameofcode.eurovoc.Parser.LANG;

import model.DataSet;
import model.Distribution;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.DataSetService;
import tripelstore.TripleStoreService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringBootWebApplication.class)
public class TestIntegrationDataset {

    @Autowired
    private DataSetService dataSetService;

    @Test
    public void importFromWebSite() throws Exception {
        for (int i = 0; i < 3; i++) {
            DateFormat df = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:SS.SSSSSS");
            for (OpenDataset dataset : Crawler.crawlAPage(i, false)) {
                EurovocAnalyser.analyse(dataset, LANG.EN, LANG.FR, LANG.DE);
                DataSet rdfDataset = new DataSet();
                rdfDataset.setDescription(dataset.description);
                rdfDataset.setTitle(dataset.title);
                rdfDataset.setPublisher(dataset.author);


                List<Distribution> dists = new ArrayList<>();
                for (Source src : dataset.sources) {
                    Distribution dist = new Distribution(src.description, src.link.toString(), src.format,
                            src.size.equals("")?0L:new Long(src.size),
                            new Date()
                            //df.parse(src.createdOn)
                            ,new Date()
                            //df.parse(src.modifiedOn)
                    );
                    dists.add(dist);
                }
                rdfDataset.setDistributionList(dists);

                dataSetService.create(rdfDataset);
            }

        }
        TripleStoreService.getInstance().writeModelToFile();
    }
}