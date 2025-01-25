package com.spadafora.taskmanager.controller;

import com.spadafora.taskmanager.model.Task;
import com.spadafora.taskmanager.service.TaskService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    @GetMapping("/")
    public List<Task> getTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return taskService.getAll(page, size);
    }

    @GetMapping("/id")
    public ResponseEntity<Task> getTaskById(@RequestParam String id) {
        try {
            final var longId = Long.parseLong(id);
            return taskService.findTaskById(longId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (NumberFormatException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description) {
        try {
            List<Task> tasks = taskService.searchTasks(title, description);
            return ResponseEntity.ok(tasks);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
