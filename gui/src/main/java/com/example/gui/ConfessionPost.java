package com.example.demo11;


import javafx.beans.property.SimpleStringProperty;

public class ConfessionPost {
    private final SimpleStringProperty ID;
    private final SimpleStringProperty content;
    private final SimpleStringProperty time;

    ConfessionPost(String ID, String content, String time){
        this.ID = new SimpleStringProperty(ID);
        this.content = new SimpleStringProperty(content);
        this.time = new SimpleStringProperty(time);
    }

    public String getID() {
        return ID.get();
    }

    public SimpleStringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }
}
