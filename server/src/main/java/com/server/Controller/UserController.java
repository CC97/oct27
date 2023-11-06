package com.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.server.Service.UserService;


import com.server.Model.UserModel;
import com.server.Request.LoginRequest;
import com.server.Request.SearchRequest;
import com.server.Request.UserRequest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getUserList(){
        List<UserModel> userList = userService.getUserList();
        return ResponseEntity.ok(userList); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws Exception{
        UserModel userModel = userService.getUserById(id);
        return ResponseEntity.ok(userModel);
    }
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        try {
            UserModel userModel = userService.createUser(userRequest);
            
            return ResponseEntity.ok(userModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> getUserByValue(@RequestBody SearchRequest searchRequest){
        List<UserModel> userList1 = userService.getUserByName(searchRequest.getValue());
        List<UserModel> userList2 = userService.getUserByPhone(searchRequest.getValue());
        Optional<UserModel> userModel = userService.getUserByEmail(searchRequest.getValue());
        List<UserModel> userResult = new ArrayList<>();
        userResult.addAll(userList1);
        userResult.addAll(userList2);
            
        if (userModel.isPresent()) {
            userResult.add(userModel.get());
        }

        return ResponseEntity.ok(userResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        try {
            UserModel userModel = userService.updateUser(id, userRequest);
            return ResponseEntity.ok(userModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            UserModel userModel = userService.deleteUser(id);
            return ResponseEntity.ok(userModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            UserModel userModel = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            if(userModel != null){
                return ResponseEntity.ok(userModel);
            } else {
                return ResponseEntity.badRequest().body("Check your email and password.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
