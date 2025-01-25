package com.spadafora.taskmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    enum status {
        PENDING,
        IN_PROGRESS,
        DONE
    }

    private String title;
    private String description;
    private String taskNum;

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskNum() {
        return this.taskNum;
    }
}
