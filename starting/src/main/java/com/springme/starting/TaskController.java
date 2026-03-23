package com.springme.starting;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<TaskResponseDTO>> getTasks(@RequestParam(required = false) String search,
                                                          @RequestParam(required = false) Boolean isDone,
                                                          @RequestParam(required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(required = false , defaultValue = "5")  Integer pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);

        if (isDone != null && search != null)
            return ResponseEntity.ok(taskService.getbyDescriptionAndIsDone(search, isDone, pageable));
        else if (isDone != null)
            return ResponseEntity.ok(taskService.findDoneTasks(isDone, pageable));
        else if (search != null)
            return ResponseEntity.ok(taskService.getByDescription(search , pageable));
        return ResponseEntity.ok(taskService.getTasks(pageable));
    }
    @PostMapping("/tasks")
    public ResponseEntity<TaskResponseDTO> addTask(@Valid @RequestBody TaskRequestDTO incomingTask)
    {
        TaskResponseDTO ts = taskService.addTask(incomingTask);
        System.out.println("Task added!\n");
        return ResponseEntity.status(HttpStatus.CREATED).body(ts);// http code for Created 201
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskbyId(@PathVariable Long id)
    {
        TaskResponseDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return "Task " + id + " deleted successfully\n";
    }
    @PutMapping("/tasks/{id}")
    public String updateTask(@PathVariable Long id, @Valid @RequestBody Task IncomingTask)
    {
        taskService.UpdateTask(id, IncomingTask);
        return "Task " + id + " updated successfully\n";
    }








}
