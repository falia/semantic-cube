package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arhscube.gameofcode.autocomplete.Autocomplete;
import com.arhscube.gameofcode.eurovoc.Parser.LANG;
import com.arhscube.gameofcode.eurovoc.Term;
import com.arhscube.gameofcode.search.Parser;
import com.arhscube.gameofcode.search.SearchTree;
import com.arhscube.gameofcode.search.Sparql;
import com.google.gson.Gson;

import model.DataSet;

@Controller
@RequestMapping("/search")
public class SearchController {

	@RequestMapping("/find")
	public String search(Map<String, Object> model, @RequestParam("search") String search) {
		System.out.println("In SearchController.search()");
		SearchTree st = Parser.parse(search, LANG.FR);
		String sparql = Sparql.toSparql(st);
		List<DataSet> dataSetList = mockDataSet();
		model.put("results", dataSetList);

		model.put("searchValue", search);

		return "search";

	}

	@ResponseBody
	@RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
	public String autocomplete(Map<String, Object> model, @RequestParam("term") String term,
			@RequestParam("lang") String lang, @RequestParam("callback") String callback) {
		List<Term> terms = Autocomplete.getAutoComplete(term, lang);
		System.out.println("hello from AutocompleteController");
		String json = new Gson().toJson(terms);
		return callback + "(" + json + ");";
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