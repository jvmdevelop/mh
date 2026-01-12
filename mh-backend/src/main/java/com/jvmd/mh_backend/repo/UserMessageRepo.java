package com.jvmd.mh_backend.repo;

import com.jvmd.mh_backend.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserMessageRepo extends JpaRepository<UserMessage, UUID> {
}
