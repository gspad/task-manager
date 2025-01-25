package com.spadafora.taskmanager.repository;

import com.spadafora.taskmanager.model.TaskDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface TaskSearchRepo extends ElasticsearchRepository<TaskDocument, String> {
    List<TaskDocument> findByTitleOrDescription(String title, String description);
}