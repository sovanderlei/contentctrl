package com.api.contentctrl.repository;

import com.api.contentctrl.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities in the database.
 * Extends JpaRepository to provide CRUD operations.
 * <p>
 * Created by: Vanderlei Soares de OLiveira
 * Creation Date: 2025-02-10
 * </p>
 * 
 * @author Vanderlei Soares de OLiveira
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object if found, otherwise null.
     */
    User findByUsername(String username);
}

