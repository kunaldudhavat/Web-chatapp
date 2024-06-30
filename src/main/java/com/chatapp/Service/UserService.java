package com.chatapp.Service;

import com.chatapp.Exception.UserException;
import com.chatapp.Model.User;
import com.chatapp.Payload.UpdateUserRequest;
import java.util.List;

public interface UserService {

    public User findUserById(Integer id) throws UserException;

    public User findUserProfile(String jwt) throws UserException;

    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException;

    public List<Object> searchUser(String query); // Update to return a list of objects (users and groups)
}
