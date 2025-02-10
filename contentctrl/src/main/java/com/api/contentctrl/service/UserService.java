package com.api.contentctrl.service;

import com.api.contentctrl.modal.User;
import com.api.contentctrl.repository.UserRepository;
import com.api.contentctrl.security.JWTUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for user-related operations, including authentication,
 * password encryption, and CRUD operations.
 * <p>
 * Created by: Vanderlei Soares de OLiveira
 * Creation Date: 2025-02-10
 * </p>
 * 
 * @author Vanderlei Soares de OLiveira
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
     * @return The user object if found.
     * @throws RuntimeException If the user is not found.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Updates an existing user's details.
     *
     * @param id The ID of the user to update.
     * @param userDetails The new details of the user.
     * @return The updated user.
     * @throws RuntimeException If the user is not found.
     */
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));  
        return userRepository.save(user);
    }

    /**
     * Deletes a user from the system by their ID.
     *
     * @param id The ID of the user to delete.
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
