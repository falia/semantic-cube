package model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataSet {

    private URL url;

    private String title;

    private String description;

    private String publisher;

    private List<Distribution> distributionList = new ArrayList<>();

    private List<String> eurovocUris = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Distribution> getDistributionList() {
        return distributionList;
    }

    public void setDistributionList(List<Distribution> distributionList) {
        this.distributionList = distributionList;
    }

    public List<String> getEurovocUris() {
        return eurovocUris;
    }

    public void setEurovocUris(List<String> eurovocUris) {
        this.eurovocUris = eurovocUris;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
