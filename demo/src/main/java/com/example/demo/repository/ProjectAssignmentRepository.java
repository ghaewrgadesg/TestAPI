package com.example.demo.repository;

import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectAssignment;
import com.example.demo.entity.ProjectAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
    @Query("SELECT p FROM ProjectAssignment p WHERE p.memberEmail = ?1 and p.projectName = ?2")
    Optional<ProjectAssignment> findProjectAssignmentByEmailAndName(String memberEmail, String projectName);

    @Modifying
    @Query("DELETE FROM ProjectAssignment p WHERE p.memberEmail = ?1 and p.projectName = ?2")
    void deleteTaskByNameAndProject(String memberEmail, String projectName);
}
