package com.chatapp.ServiceImpl;

import com.chatapp.Exception.UserException;
import com.chatapp.Model.User;
import com.chatapp.Payload.UpdateUserRequest;
import com.chatapp.Repository.UserRepository;
import com.chatapp.Service.UserService;
import com.chatapp.config.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public User findUserById(Integer id) throws UserException {
        return this.userRepository.findById(id).orElseThrow(() -> new UserException("The requested user is not found"));
    }

    @Override
    public User findUserProfile(String jwt) throws UserException {
        String email = this.tokenProvider.getEmailFromToken(jwt);

        if (email == null) {
            throw new BadCredentialsException("Received invalid token...");
        }

        User user = this.userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("No user found with the given email");
        }
        return user;
    }

    @Override
    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException {
        User user = this.findUserById(userId);

        if (req.getName() != null) {
            user.setName(req.getName());
        }

        if (req.getProfile() != null) {
            user.setProfile(req.getProfile());
        }
        return this.userRepository.save(user);
    }

    @Override
    public List<User> searchUser(String query) {
        List<User> users = this.userRepository.searchUser(query);
        return users;
    }

}