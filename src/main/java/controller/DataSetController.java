package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by piraujo on 11/03/2017.
 */
@Controller
public class DataSetController
{
    @RequestMapping("/datasetupload")
    public String welcome(Map<String, Object> model) {
        System.out.println("hello from DataSetFormUploadController");
        return "dataSetUploadForm";
    }
}
