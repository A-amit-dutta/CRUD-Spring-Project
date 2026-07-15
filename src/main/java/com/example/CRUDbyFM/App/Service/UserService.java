package com.example.CRUDbyFM.App.Service;

import com.example.CRUDbyFM.App.Exceptions.UserNotFoundException;
import com.example.CRUDbyFM.App.Model.User;

import com.example.CRUDbyFM.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService() {
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
            if(userRepository.existsById(user.getId())) return userRepository.save(user);
//        return null;
        throw new UserNotFoundException("User with ID "+user.getId() + " does not exist");
    }

    /*  DEFAULT EXCEPTION
    *
    * *{
    "timestamp": "2026-06-21T15:46:04.250Z",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/users"
    }
    *
    * */



    public boolean deleteUser(int id) {

        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User with id "+id+" does not exist.");
        }

        userRepository.deleteById(id);
        return true;
    }

    public List<User> getAllUser() {

        List<User> list = userRepository.findAll();

        if (!list.isEmpty())
            return list;
        throw new UserNotFoundException("NO Users exist in the database");

    }

    public User getUserById(int id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User with ID " + id + " does not exist"));
    }

    public List<User> searchUser(String name, String email) {

        if (name != null && email != null) {
            return userRepository.findByNameIgnoreCaseAndEmailIgnoreCase(name, email);
        }
        if (name != null) {
            return userRepository.findByNameIgnoreCase(name);
        }
        if (email != null) {
            return userRepository.findByEmailIgnoreCase(email);
        }
        return userRepository.findAll();
    }

//    public List<User> searchUser(String name, String email) {
//
//        return userRepository.findAll().stream()
//                .filter(user ->
//                        (name == null || user.getName().equalsIgnoreCase(name)) &&
//                                (email == null || user.getEmail().equalsIgnoreCase(email))
//                )
//                .toList();
//    }
}
