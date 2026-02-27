package com.springme.starting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController
{
    private final TaskService taskService;

    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
        System.out.println("===========================================");
        System.out.println(">>> 2. TaskController Constructor Called! <<<");
        System.out.println("===========================================");
    }
    @GetMapping("/tasks")
    public ResponseEntity<Iterable<Task>> getTasks(@RequestParam(required = false) String search,  @RequestParam(required = false) Boolean isDone)
    {
        if (isDone != null && search != null)
            return ResponseEntity.ok(taskService.getbyDescriptionAndIsDone(search, isDone));
        else if (isDone != null)
            return ResponseEntity.ok(taskService.findDoneTasks(isDone));
        else if (search != null)
            return ResponseEntity.ok(taskService.getByDescription(search));
        return ResponseEntity.ok(taskService.getTasks());
    }
    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@Valid @RequestBody Task task)
    {
        Task ts = taskService.addTask(task);
        System.out.println("Task added!\n");
        return ResponseEntity.status(HttpStatus.CREATED).body(ts);//201
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskbyId(@PathVariable Long id)
    {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return "Task " + id + " deleted successfully\n";
    }
    @PutMapping("/tasks/{id}")
    public String updateTask(@PathVariable Long id, @Valid @RequestBody Task task)
    {
        taskService.UpdateTask(id, task);
        return "Task " + id + " updated successfully\n";
    }








}
