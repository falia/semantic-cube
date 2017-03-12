package controller;

import model.DataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.DataSetService;
import service.SparqlServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by piraujo on 11/03/2017.
 */
@Controller
public class DataSetController
{

    /* The Logger */
    private static final Logger log = LoggerFactory.getLogger(DataSetController.class);

    @Autowired
    private DataSetService datasetService;

    @GetMapping(value = "/datasetupload")
    public String loadForn(Model model) {
        log.debug("Load dataset form");
        model.addAttribute("dataset", new DataSet());
        return "dataSetUploadForm";
    }

    @PostMapping(value = "/datasetupload")
    public String addDataset(@ModelAttribute DataSet dataset, @RequestParam  ("themeSelect")String themes){
        if(null != themes ) {
            List<String> list = Arrays.stream(themes.split(" ")).collect(Collectors.toList());
            dataset.setEurovocUris(list);
        }
        datasetService.create(dataset);
        return "index";
    }
}
