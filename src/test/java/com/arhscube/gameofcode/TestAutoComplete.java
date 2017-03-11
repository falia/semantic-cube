package com.arhscube.gameofcode;

import com.arhscube.gameofcode.autocomplete.Autocomplete;
import com.arhscube.gameofcode.eurovoc.Term;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


/**
 * Created by piraujo on 11/03/2017.
 */
public class TestAutoComplete {

    @Test
    public void name() throws Exception {
        List<Term> autoCompl = Autocomplete.getAutoComplete("vie","fr");
        Assert.assertEquals(20,autoCompl.size());
    }




}
