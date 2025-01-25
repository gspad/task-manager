package com.spadafora.taskmanager.repository;

import com.spadafora.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    Optional<Task> findById(long id);
    Optional<Task> findByTaskNum(String taskNum);
}
