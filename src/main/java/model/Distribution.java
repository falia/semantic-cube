package model;

import java.util.Date;

public class Distribution {

    private String description;

    private String downloadURL;

    private String format;

    private long byteSize;

    private Date issued;

    private Date modified;

    public Distribution(String description, String downloadURL, String format, long byteSize, Date issued, Date modified) {
        this.description = description;
        this.downloadURL = downloadURL;
        this.format = format;
        this.byteSize = byteSize;
        this.issued = issued;
        this.modified = modified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getByteSize() {
        return byteSize;
    }

    public void setByteSize(long byteSize) {
        this.byteSize = byteSize;
    }

    public Date getIssued() {
        return issued;
    }

    public void setIssued(Date issued) {
        this.issued = issued;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
