package com.ToDoApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class taskData {

//variables
    private String item;

    @JsonProperty("isCompleted")
    private Boolean isCompleted;


    private String userID;
    private String createdAt;

    @JsonProperty("__v")
    @JsonIgnore()
    private int version;

    @JsonProperty("_id")
    private String taskID;




    //Constructor
    public taskData() {}

    public taskData(String item, Boolean isCompleted) {
        this.item = item;
        this.isCompleted = isCompleted;
    }

    public taskData(String item) {
        this.item = item;
    }


    //setters and getters
    public String getItem() {
        return item;
    }

    @JsonProperty("isCompleted")
    public Boolean getCompleted() {
        return isCompleted;
    }

    @JsonProperty("isCompleted")
    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public void setItem(String item) {
        this.item = item;
    }



    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


}
