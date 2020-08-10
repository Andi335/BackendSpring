package com.backendSpring.services;

import com.backendSpring.models.User;
import com.backendSpring.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public Collection<User> getAllUser() {
        return userRepository.getAllUser();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public void deleteUserById(int id) {
        this.userRepository.deleteUserById(id);
    }

    public User updateUser(User updatedUser) {
        return userRepository.updateUser(updatedUser);
    }

    public void postUser(User user) {
        userRepository.postUser(user);
    }
}
