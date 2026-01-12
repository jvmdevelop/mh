package com.jvmd.mh_backend.repo;

import com.jvmd.mh_backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepo extends JpaRepository<Task, UUID> {
}
