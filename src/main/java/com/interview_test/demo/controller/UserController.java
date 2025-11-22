package com.interview_test.demo.controller;

import com.interview_test.demo.model.User;
import com.interview_test.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllUsers(){
        List<User> listUsers = userService.getAllUsers();
        return ResponseEntity.ok(listUsers);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUser(@PathVariable Long id){
        User fetchedUser = userService.getUser(id);
        if (fetchedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("Error", "User ID " + id + " not found"));
        }
        return ResponseEntity.ok(fetchedUser);
    }

    @PostMapping(value = "/",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @PutMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateUser(@PathVariable Long id,  @RequestBody  User user){
        User updatedUser = userService.updateUser(id, user);
        if(updatedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "Error", "User ID " + id + " not found"
                    ));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("Message", "User ID " + id + " has been updated");
        response.put("User", updatedUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User deletedUser = userService.deleteUser(id);
        if (deletedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("Error", "User ID " + id + " not found"));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("Message", "User ID " + id + " has been deleted");
        response.put("User", deletedUser);
        return ResponseEntity.ok(response);
    }
}
