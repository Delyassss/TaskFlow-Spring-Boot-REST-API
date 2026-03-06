package com.springme.starting;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskResponseDTO
{
    private long id;
    private String description;
    //@JsonProperty("isDone")
    private Boolean isDone;


    public TaskResponseDTO() {}

    public TaskResponseDTO(long id, String description, boolean isDone)
    {
        this.id = id;
        this.description = description;
        this.isDone = isDone;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //@JsonProperty("isDone")
    public Boolean getIsDone() {
        return isDone;
    }
    //@JsonProperty("isDone")
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
