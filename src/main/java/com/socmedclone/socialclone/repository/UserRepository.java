package com.socmedclone.socialclone.repository;

import com.socmedclone.socialclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User getById(Long id);
    User findAllByUsername(String username);
}
