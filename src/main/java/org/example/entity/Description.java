package org.example.entity;

import java.util.Date;

public class Description {
    Date untilDate;
    String location;
    String text;

    public Description(Date untilDate, String location, String text) {
        this.untilDate = untilDate;
        this.location = location;
        this.text = text;
    }

    public Description() {
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
