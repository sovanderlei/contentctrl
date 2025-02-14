package com.api.contentctrl.service;

import com.api.contentctrl.modal.User;
import com.api.contentctrl.repository.UserRepository;
import com.api.contentctrl.security.JWTUtil;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for user-related operations, including authentication,
 * password encryption, and CRUD operations.
 * <p>
 * Created by: Vanderlei Soares de Oliveira
 * Creation Date: 2025-02-10
 * </p>
 * 
 * @author Vanderlei Soares de Oliveira
 * @version 1.0
 */
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    /**
     * Constructor for UserService, initializing dependencies.
     */
    @Autowired
    public UserService(UserRepository userRepository, JWTUtil jwtUtil) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Encrypts a given password.
     *
     * @param rawPassword The raw password to be encoded.
     * @return The encoded password.
     */
    public String getPasswordEncoder(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Authenticates a user and generates a JWT token if successful.
     *
     * @param username The username of the user.
     * @param password The raw password of the user.
     * @return A JWT token if authentication is successful.
     * @throws RuntimeException If the username is not found or credentials are invalid.
     */
    public String authenticateUser(String username, String password) {
    	 
    	User user = userRepository.findByUsername(username);
         
        if (user == null) {
            throw new RuntimeException("User not found");
        }
         
        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list containing all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();  
    }

    /**
     * Creates a new user and stores it in the database.
     *
     * @param user The user object containing the user details.
     * @return The created user.
     */
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); 
        return userRepository.save(user);  
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to fetch.
     * @return An Optional containing the user if found, otherwise empty.
     * @throws RuntimeException If the user is not found.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Updates an existing user's details.
     *
     * @param id The ID of the user to update.
     * @param userDetails The new details of the user.
     * @return An Optional containing the updated user if found, otherwise empty.
     * @throws RuntimeException If the user is not found.
     */
    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));  
            return userRepository.save(existingUser);
        });
    }

    /**
     * Deletes a user from the system by their ID.
     *
     * @param id The ID of the user to delete.
     * @return True if the user was deleted, false if not found.
     */
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
