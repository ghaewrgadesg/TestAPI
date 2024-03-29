package com.example.demo.repository;

import com.example.demo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository
        extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p WHERE p.name = ?1")
    Optional<Project> findProjectByName(String name);
}
