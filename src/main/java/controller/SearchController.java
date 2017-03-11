package controller;

import model.DataSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {

    @RequestMapping("/find")
    public String search(Map<String, Object> model, @RequestParam("search") String search) {
        System.out.println("In SearchController.search()");

        List<DataSet> dataSetList = mockDataSet();
        model.put("results", dataSetList);

        model.put("searchValue", search);

        return "search";

    }

    public List<DataSet> mockDataSet() {
        List<DataSet> dataSetList = new ArrayList<>();

        DataSet one = new DataSet();
        one.setTitle("Dataset one");
        one.setDescription("This is a description");

        DataSet two = new DataSet();
        two.setTitle("Dataset two");
        two.setDescription("This is a description");

        DataSet three = new DataSet();
        three.setTitle("Dataset three");
        three.setDescription("This is a description");

        dataSetList.add(one);
        dataSetList.add(two);
        dataSetList.add(three);

        return dataSetList;
    }

}