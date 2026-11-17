package com.example.userservice.service;

import com.example.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final Map<Long, User> users = new ConcurrentHashMap<>();

    public UserService() {
        users.put(1L, new User(1L, "Arun", "Testing"));
        users.put(2L, new User(2L, "Priya", "Development"));
    }

    public List<User> getAllUsers() {
        return List.copyOf(users.values());
    }

    public User getUserById(Long id) {
        return users.get(id);
    }

    public User addUser(User user) {
        if (user.getId() == null) {
            long nextId = users.keySet().stream().mapToLong(Long::longValue).max().orElse(0) + 1;
            user.setId(nextId);
        }
        users.put(user.getId(), user);
        return user;
    }
}
