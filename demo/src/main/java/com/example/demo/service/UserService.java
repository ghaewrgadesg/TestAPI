package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()){
            throw new IllegalStateException("email already in used");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists){
            throw new IllegalStateException("User with Id: " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }
    @Transactional
    public void updateUser(Long userId, String name, String email) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException
                ("User with Id: " + userId + " does not exist"));

        if (name != null && name.length() > 0 &&
                !Objects.equals(user.getName(), name)){
            user.setName(name);
        }

        if (email != null && email.length() > 0 &&
                !Objects.equals(user.getEmail(), email)){
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()){
                throw new IllegalStateException("email already in used");
            }
            user.setEmail(email);
        }
    }
}
