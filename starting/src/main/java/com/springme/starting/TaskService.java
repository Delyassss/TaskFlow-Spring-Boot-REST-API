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
    public Iterable<TaskResponseDTO> getTasks()
    {
        Iterable<Task> tasks = taskRepository.findAll();
        return ListtoTaskResponseDTO(tasks);
    }
    public TaskResponseDTO addTask(TaskRequestDTO task)
    {
        Task turnRequestoTask = RequesttoTask(task);
        Task finalTs = taskRepository.save(turnRequestoTask);
        return convertToTaskResponseDTO(finalTs);
    }
    public Iterable<TaskResponseDTO> findDoneTasks(boolean isdone)
    {
        Iterable<Task> ts = taskRepository.findByIsDone(isdone);
        return  (ListtoTaskResponseDTO(ts));
    }
    public Iterable<TaskResponseDTO> getByDescription(String description)
    {
        Iterable<Task> ts =  taskRepository.findByDescriptionContainingIgnoreCase(description);
        return  (ListtoTaskResponseDTO(ts));
    }
    public Iterable<TaskResponseDTO> getbyDescriptionAndIsDone(String description, boolean isdone)
    {
        Iterable<Task> ts = taskRepository.findByDescriptionContainingIgnoreCaseAndIsDone(description, isdone);
        return  ListtoTaskResponseDTO(ts);
    }
    public int deleteTask(Long id)
    {
        taskRepository.findById(id).orElseThrow(()->new TaskNotFoundExp(id));
        taskRepository.deleteById(id);
        return 1;

    }
    public TaskResponseDTO getTaskById(Long id)//Key point: .orElseThrow() turns an Optional<Task> into a Task. Returning it as Optional<Task> is wrong; it’s already “unwrapped.”
    {
        Task ts = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundExp(id));
        return  convertToTaskResponseDTO(ts);
    }

    public void UpdateTask(Long id, Task task)
    {
            Task task1 = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundExp(id));
            task1.setDescription(task.getDescription());
            task1.setIsDone(task.getIsDone());
            taskRepository.save(task1);
            System.out.println("Updated task " + id + "\n");
    }
    public TaskResponseDTO convertToTaskResponseDTO(Task task)
    {
        return new TaskResponseDTO(task.getId(), task.getDescription(), task.getIsDone());
    }
    public Iterable<TaskResponseDTO> ListtoTaskResponseDTO(Iterable<Task> tasks)
    {
        List<TaskResponseDTO> dtos = new ArrayList<>();
        for (Task ts : tasks)
        {
            dtos.add(convertToTaskResponseDTO(ts));
        }
        return  dtos;
    }
    public Task RequesttoTask(TaskRequestDTO request)
    {
        Task ts =  new Task();

        ts.setDescription(request.getDescription());
        return ts;
    }


}