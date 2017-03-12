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

        Parser.LANG language = Parser.getLangCode(lang);
        List<Term> terms=Parser.getAllTerms(language);
        List<Term>toAutocomplete = terms.stream().filter(p->p.getLabel().startsWith(term)).collect(Collectors.toList());
        return toAutocomplete;
    }
}
