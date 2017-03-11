package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by piraujo on 11/03/2017.
 */
@Controller
public class DataSetController
{
    @RequestMapping(value = "/datasetupload")
    public String welcome(Map<String, Object> model) {
        System.out.println("hello from DataSetController");

        return "dataSetUploadForm";
    }
}
