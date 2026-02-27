package com.springme.starting;

public class TaskNotFoundExp extends RuntimeException
{
    public TaskNotFoundExp(Long id)
    {
        super("Task with id " + id + " not found");
    }

}
