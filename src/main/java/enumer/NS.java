package enumer;

/**
 * Created by aliafa on 3/11/2017.
 */
public enum NS {

    DATA_SET("https://data.public.lu/en/dataset"),

    DISTRIBUTION("https://data.public.lu/en/distribution"),

    DCAT("http://www.w3.org/ns/dcat#");

    String url;

    NS(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
