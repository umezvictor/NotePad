package com.example.notetaker;

public class Note {
    //this class is the data structure class that helps manipulate data in db
    //this class will have the variables that will hold the data froma addnote activity
    //it will pass data to the sqlite database
    //these are the fields the database will have

    //this note class will be used to interact with the db
    private long Id;
    private String title;
    private String content;
    private String date;
    private String time;

    //create multiple constructors
    //no parameter
    Note(){}

    //all paramaters, but no id -- gets called when a record is first created, since record won't
    //have an id yet until it is inserted into db
    Note(String title, String content, String date, String time){
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    Note(long id, String title, String content, String date, String time){
        this.Id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    //create getters and setters for the private fields declared above
    //getters for retrieving data
    //setter for adding data

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
