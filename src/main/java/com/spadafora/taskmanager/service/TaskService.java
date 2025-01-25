package com.spadafora.taskmanager.service;

import co.elastic.clients.elasticsearch.ml.Page;
import com.spadafora.taskmanager.model.Task;
import com.spadafora.taskmanager.model.TaskDocument;
import com.spadafora.taskmanager.repository.TaskRepo;
import com.spadafora.taskmanager.repository.TaskSearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepository;

    @Autowired
    private TaskSearchRepo taskSearchRepository;

    public void createTask(Task task) {
        // Save to PostgreSQL
        Task savedTask = taskRepository.save(task);

        // Index in Elasticsearch
        TaskDocument taskDocument = new TaskDocument(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getTaskNum()
        );

        taskSearchRepository.save(taskDocument);
    }

    public List<Task> searchTasks(String title, String description) {
        // Search in Elasticsearch
        List<TaskDocument> taskDocuments = taskSearchRepository.findByTitleOrDescription(title, description);

        // Fetch details from PostgreSQL
        List<Long> taskIds = taskDocuments.stream()
                .map(TaskDocument::getId)
                .collect(Collectors.toList());

        return taskRepository.findAllById(taskIds);
    }

    public Optional<Task> findTaskById(long taskId) {
        return taskRepository.findById(taskId);
    }

    public Optional<Task> findTaskByTaskNum(String taskNum) {
        return taskRepository.findByTaskNum(taskNum);
    }

    public List<Task> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        final var tasksPage = taskRepository.findAll(pageable);
        return tasksPage.stream().toList();
    }
}