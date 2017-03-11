package model;

import java.util.ArrayList;
import java.util.List;

public class DataSet {

    private String title;

    private String description;

    private String publisher;

    private String theme;

    private List<Distribution> distributionList;

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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Distribution> getDistributionList() {
        if(distributionList == null) {
            this.distributionList = new ArrayList<>();
        }
        return distributionList;
    }

    public void setDistributionList(List<Distribution> distributionList) {
        this.distributionList = distributionList;
    }
}
