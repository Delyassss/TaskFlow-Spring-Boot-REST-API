package com.springme.starting;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequestDTO
{
    @NotBlank(message = "Task description cannot be empty!")
    @Size(min = 3, max = 255, message = "Description must be 3-255 characters long" )
    private String description;
    //private boolean isDone; we add it cuz logically a brand-new task cant be done yet

    public  TaskRequestDTO(){}
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public  TaskRequestDTO(String description)
    {
        this.description = description;
    }
}
