package controller;

import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import service.SparqlService;

import java.io.ByteArrayOutputStream;

@Controller
public class SparqlEndpointController {

    @Autowired
    private SparqlService sparqlService;

    @RequestMapping("/sparql")
    public String sparql() {
        return "sparql";
    }


    @RequestMapping("/sparql/endpoint")
    public  @ResponseBody String endpoint(@RequestParam("query") String query, @RequestParam("format") String format) {
        if(!sparqlService.isQueryValid(query)) return "The sparql query is not valid";

        ResultSet rs = sparqlService.readSparqlQuery(query);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        if("json".equalsIgnoreCase(format)) {
            ResultSetFormatter.outputAsJSON(outputStream, rs);
        } else if ("xml".equalsIgnoreCase(format)) {
            ResultSetFormatter.outputAsXML(outputStream, rs);
        } else if ("csv".equalsIgnoreCase(format)) {
            ResultSetFormatter.outputAsCSV(outputStream, rs);
        } else  {
            return "format [" + format + "] not supported";
        }

        return new String(outputStream.toByteArray());
    }
}
