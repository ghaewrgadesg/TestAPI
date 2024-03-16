package com.example.demo.repository;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository
    extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.name = ?1 and t.projectName = ?2")
    Optional<Task> findTaskByNameAndProject(String name, String projectName);
    @Modifying
    @Query("DELETE FROM Task t WHERE t.name = ?1 AND t.projectName = ?2")
    void deleteTaskByNameAndProject(String name, String projectName);
}
