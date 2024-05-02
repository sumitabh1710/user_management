package com.example.usermanagement.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.usermanagement.Dto.UserLoginDto;
import com.example.usermanagement.Entity.User;
import com.example.usermanagement.Repo.UserRepository;
import com.example.usermanagement.Utils.JwtTokenUtil;

@Service
public class UserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> registerUser(User user) {

        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty()) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "All fields are required");

            return ResponseEntity.badRequest().body(responseBody);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Email already exists !");

            return ResponseEntity.badRequest().body(responseBody);
        }
        
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok().body(savedUser);
    }

    public ResponseEntity<Object> loginUser(UserLoginDto userLoginDto) {

        if (!userRepository.existsByEmail(userLoginDto.getEmail())) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Email does not exists !");

            return ResponseEntity.badRequest().body(responseBody);
        }

        User user = userRepository.findByEmail(userLoginDto.getEmail());

        if (!user.getPassword().equals(userLoginDto.getPassword())) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Password does not match !");

            return ResponseEntity.badRequest().body(responseBody);
        }

        String jwtToken = jwtTokenUtil.generateToken(user);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", jwtToken);

        return ResponseEntity.ok().body(responseBody);
    }

    public ResponseEntity<Object> updateUser(Long id, User updatedUser) {

        User existingUser = userRepository.getById(id);

        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        User existingUserWithEmail = userRepository.findByEmail(updatedUser.getEmail());
        if (existingUserWithEmail != null && !existingUserWithEmail.getId().equals(existingUser.getId())) {

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Email already exists !");

            return ResponseEntity.badRequest().body(responseBody);
        }

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setSpeciality(updatedUser.getSpeciality());
        existingUser.setEmail(updatedUser.getEmail());

        userRepository.save(existingUser);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "User details updated successfully !");

        return ResponseEntity.ok().body(responseBody);
    }

}
