package com.spadafora.taskmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "tasks")
public class TaskDocument {
    @Id
    private long id;
    private String title;
    private String description;
    private String taskNum;

    public TaskDocument() {}

    public TaskDocument(long id, String title, String description, String taskNum) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskNum = taskNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }
}