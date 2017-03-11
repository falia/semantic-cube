package com.arhscube.gameofcode.datapublic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.arhscube.gameofcode.eurovoc.Term;

class OpenDataset {
	@Override
	public String toString() {
		return "OpenDataset [title=" + title + ", sources=" + sources + ", eurovoc=" + eurovoc + ", description="
				+ description + ", author=" + author + ", keywords=" + keywords + "]";
	}
	String title;
	List<Source> sources = new ArrayList<>();
	Set<Term> eurovoc = new HashSet<>();
	public String description;
	public String author;
	public List<String> keywords = new ArrayList<>();
}

