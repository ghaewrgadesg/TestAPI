package com.example.demo.repository;

import com.example.demo.entity.ProjectAssignment;
import com.example.demo.entity.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskAssignmentRepository extends
        JpaRepository<TaskAssignment, Long> {
    @Query("SELECT t FROM TaskAssignment t WHERE t.memberEmail = ?1 and t.taskName=?2 and t.projectName = ?3")
    Optional<TaskAssignment> findTaskAssignmentByEmailAndName(String memberEmail,String taskName, String projectName);

    @Modifying
    @Query("DELETE FROM TaskAssignment t WHERE t.memberEmail = ?1 and t.taskName=?2 and t.projectName = ?3")
    void deleteUserByNameAndTask(String memberEmail,String taskName, String projectName);
}
