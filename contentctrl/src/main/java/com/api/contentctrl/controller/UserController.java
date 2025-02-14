package com.api.contentctrl.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.api.contentctrl.modal.User;
import com.api.contentctrl.service.UserService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/contentctrl/users")
@Tag(name = "User Controller", description = "Operations related to users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Register a new user", description = "Registers a new user in the system.")
    @ApiResponse(responseCode = "200", description = "User successfully registered", 
                 content = @Content(schema = @Schema(implementation = User.class)))
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
        @Parameter(description = "Information of the user to be registered") @RequestBody User user) { 
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);  // Status 201 Created
    }

    @Operation(summary = "Authenticate user", description = "Performs user login and returns a JWT token.")
    @ApiResponse(responseCode = "200", description = "Authentication successful", 
                 content = @Content(schema = @Schema(type = "string", example = "Bearer <JWT_TOKEN>")))
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
        @Parameter(description = "User credentials for login") @RequestBody User user) {
        try {
            String token = userService.authenticateUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok("Bearer " + token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @Operation(summary = "Get all users", description = "Returns a list of all registered users.")
    @ApiResponse(responseCode = "200", description = "Users found successfully", 
                 content = @Content(schema = @Schema(implementation = User.class)))
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);  // Status 200 OK
    }

    @Operation(summary = "Create a new user", description = "Creates a new user in the system.")
    @ApiResponse(responseCode = "201", description = "User successfully created", 
                 content = @Content(schema = @Schema(implementation = User.class)))
    @PostMapping
    public ResponseEntity<User> createUser(
        @Parameter(description = "Information of the new user to be created") @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);  // Status 201 Created
    }

    @Operation(summary = "Get user by ID", description = "Returns details of a user based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "User found successfully", 
                 content = @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
        @Parameter(description = "ID of the user to be retrieved") @PathVariable Long id) {
        return userService.getUserById(id)
            .map(user -> ResponseEntity.ok(user))  // Status 200 OK
            .orElseGet(() -> ResponseEntity.status(404).build());  // Status 404 Not Found
    }

    @Operation(summary = "Update user", description = "Updates the details of an existing user.")
    @ApiResponse(responseCode = "200", description = "User successfully updated", 
                 content = @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
        @Parameter(description = "ID of the user to be updated") @PathVariable Long id,
        @Parameter(description = "User data to be updated") @RequestBody User user) {
        return userService.updateUser(id, user)
            .map(updatedUser -> ResponseEntity.ok(updatedUser))  // Status 200 OK
            .orElseGet(() -> ResponseEntity.status(404).build());  // Status 404 Not Found
    }

    @Operation(summary = "Delete user", description = "Deletes a user based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "User successfully deleted")
    @ApiResponse(responseCode = "404", description = "User not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "ID of the user to be deleted") @PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.status(200).build();  // Status 200 OK
        } else {
            return ResponseEntity.status(404).build();  // Status 404 Not Found
        }
    }
}
