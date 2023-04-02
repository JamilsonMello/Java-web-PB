package com.jamilson.projectmanager.dto;

import java.util.Date;

public class ProjectDTO {

    private String title;
    private String description;
    private Date date;
    private Date createdAt;

    public ProjectDTO(String title, String description, Date date, Date createdAt) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.createdAt = createdAt;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
