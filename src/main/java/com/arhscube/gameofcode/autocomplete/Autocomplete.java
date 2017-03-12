package com.arhscube.gameofcode.autocomplete;

import com.arhscube.gameofcode.eurovoc.Parser;
import com.arhscube.gameofcode.eurovoc.Term;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by piraujo on 11/03/2017.
 */
public class Autocomplete {
    public static List<Term> getAutoComplete(@RequestParam("term") String term, @RequestParam("lang") String lang) {
        List<Term> terms=new ArrayList<>();
        switch (lang){
            case "fr": terms= Parser.getAllTerms(Parser.LANG.FR);break;
            case "de": terms=Parser.getAllTerms(Parser.LANG.DE);break;
            case "en": terms=Parser.getAllTerms(Parser.LANG.EN);break;
            case "pt": terms=Parser.getAllTerms(Parser.LANG.PT);break;
            default:break;
        }
        List<Term>toAutocomplete = terms.stream().filter(p->p.getLabel().startsWith(term)).collect(Collectors.toList());
        return toAutocomplete;
    }
}
