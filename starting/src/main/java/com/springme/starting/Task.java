package com.springme.starting;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 3, max = 255, message = "Description must be 3-255 characters long" )
        private  String description;
   private boolean isDone;


   public Task() {};
   public Task(String description, boolean isDone)
   {
       this.description = description;
       this.isDone = isDone;
   }
   public long getId()
   {
       return id;
   }
    public  void setId(long id)
    {
        this.id = id;
    }
    public String getDescription()
    {
        return description;
    }
    public boolean getIsDone()
    {
        return isDone;
    }
    public  void setIsDone(boolean isDone)
    {
        this.isDone = isDone;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
}

