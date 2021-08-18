package com.example.usercrud.service.serviceImpl;

import com.example.usercrud.dto.UserDto;
import com.example.usercrud.exception.ApiRequestException;
import com.example.usercrud.model.User;
import com.example.usercrud.repository.UserRepository;
import com.example.usercrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return user;
        }
        else{
            throw new ApiRequestException("User does not exist");
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new ApiRequestException("No user found in the database");
        }
        return users;
    }

    @Override
    public User registerUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        User user = new User();
        if(optionalUser.isEmpty()) {
            return saveUser(user,userDto);
        }else{
            throw new ApiRequestException("User with email "+ userDto.getEmail()+" already exist");
        }
    }



    @Override
    public String deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            userRepository.delete(user);
            return "User deleted successfully";
             }
        else{
            throw new ApiRequestException("User does not exist");
        }
    }

    @Override
    public User updateUser(Long userId, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return saveUser(user,userDto);
        }else {
            throw new ApiRequestException("User does not exist");
        }

    }

    @Override
    public User saveUser(User user, UserDto userDto) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return user;
    }
}
