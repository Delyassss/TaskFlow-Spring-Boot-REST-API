package com.springme.starting;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService
{
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository)
    {
        this.taskRepository = taskRepository;
        System.out.println("=========================================");
        System.out.println(">>> 1. TaskService Constructor Called! <<<");
        System.out.println("=========================================");
    }
    public Iterable<Task> getTasks()
    {
        return taskRepository.findAll();
    }
    public Task addTask(Task task)
    {
        return taskRepository.save(task);
    }
    public Iterable<Task> findDoneTasks(boolean isdone)
    {
        return taskRepository.findByIsDone(isdone);
    }
    public Iterable<Task> getByDescription(String description)
    {
        return taskRepository.findByDescriptionContainingIgnoreCase(description);
    }
    public Iterable<Task> getbyDescriptionAndIsDone(String description, boolean isdone)
    {
        return taskRepository.findByDescriptionContainingIgnoreCaseAndIsDone(description, isdone);
    }
    public int deleteTask(Long id)
    {
        taskRepository.findById(id).orElseThrow(()->new TaskNotFoundExp(id));
        taskRepository.deleteById(id);
        return 1;

    }
    public Task getTaskById(Long id)//Key point: .orElseThrow() turns an Optional<Task> into a Task. Returning it as Optional<Task> is wrong; it’s already “unwrapped.”
    {
        return taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundExp(id));
    }

    public void UpdateTask(Long id, Task task)
    {
            Task task1 = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundExp(id));
            task1.setDescription(task.getDescription());
            task1.setIsDone(task.getIsDone());
            taskRepository.save(task1);
            System.out.println("Updated task " + id + "\n");
    }

}