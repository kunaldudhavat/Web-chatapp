package com.chatapp.Service;

import com.chatapp.Exception.UserException;
import com.chatapp.Model.User;
import com.chatapp.Payload.UpdateUserRequest;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface UserService {

    public User findUserById(Integer id) throws UserException;

    public User findUserProfile(String jwt) throws UserException;

    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException;

    public List<User> searchUser(String query);
}