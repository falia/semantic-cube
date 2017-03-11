package com.arhscube.gameofcode.datapublic;

import java.net.URL;

class Source {
	@Override
	public String toString() {
		return "Source [link=" + link + ", format=" + format + ", description=" + description + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + "]";
	}
	URL link;
	String format;
	String description;
	String createdOn;
	String modifiedOn;
}
