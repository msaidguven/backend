package com.project.soruCevap.services;

import com.project.soruCevap.entities.User;
import com.project.soruCevap.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User createUser(User newUser) {
        String encryptedPassword = this.passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encryptedPassword);
        newUser.setUserCreateTime(new Date());
        return userRepository.save(newUser);
    }

    public User getOneUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(String userId, User updateUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user != null){
            User toUser = updateUser;
            toUser.setId(updateUser.getId());
            toUser.setFirstName(updateUser.getFirstName());
            toUser.setLastName(updateUser.getLastName());
            toUser.setEmail(updateUser.getEmail());
            //toUser.setPassword(updateUser.getPassword());
            return userRepository.save(toUser);
        }
        return null;
    }

    public Boolean deleteUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            userRepository.deleteById(userId);
            return true;
        }else {
            return false;
        }
    }
}
