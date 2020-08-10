package com.backendSpring.controller;


import com.backendSpring.models.User;

import com.backendSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;


@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserService userservice;

    @GetMapping
    public Collection<User> getAllUsers() {
        return userservice.getAllUser();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userservice.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUserById(@PathVariable("id") int id) {
        userservice.deleteUserById(id);
    }

    @PostMapping
    public ResponseEntity<Object> postUser(@RequestBody User user) {

        userservice.postUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody User updatedUser) {
        User user = userservice.getUserById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
                User newUser = userservice.updateUser(updatedUser);
                return new ResponseEntity<>(newUser, HttpStatus.OK);

    }

    @PostMapping("/{id}/upload")
    public ResponseEntity uploadFile(@PathVariable("id") int id, @RequestParam("file") @RequestBody MultipartFile file) {
        try {
            User user = userservice.getUserById(id);
            user.setUserFile(file.getBytes());
            user.setFileName(file.getOriginalFilename());
            userservice.postUser(user);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}/download", produces = "application/pdf")
    public ResponseEntity<byte[]> ByteToFile(@PathVariable("id") int id) {
        try {

            User user = userservice.getUserById(id);
            byte[] bytes = user.getUserFile();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData(user.getFileName(), user.getFileName());
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}/file")
    public ResponseEntity deleteFileById(@PathVariable int id) {
        User user = userservice.getUserById(id);
        user.setUserFile(null);
        user.setFileName(null);
        userservice.postUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
