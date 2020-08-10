package com.backendSpring.repos;

import com.backendSpring.models.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private static Map<Integer, User> user = new HashMap<>();

    static {
        user = new HashMap<>() {
            {
                put(1, new User(1, "Tim", "Welter"));
                put(2, new User(2, "Paul", "Reiner"));

            }
        };
    }

    public Collection<User> getAllUser() {
        return this.user.values();
    }

    public User getUserById(int id) {
        return this.user.get(id);
    }

    public void deleteUserById(int id) {
        this.user.remove(id);
    }

    public User  updateUser(User updatedUser) {
        return this.user.put(updatedUser.getUserId(), updatedUser);
    }

    public void postUser(User user) {
        this.user.put(user.getUserId(), user);
    }


}
