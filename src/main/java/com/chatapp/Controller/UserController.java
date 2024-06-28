package com.chatapp.Controller;

import com.chatapp.Exception.UserException;
import com.chatapp.Payload.ApiResponse;
import com.chatapp.Payload.UpdateUserRequest;
import com.chatapp.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chatapp.Model.User;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
        logger.info("Fetching profile for token: " + token);
        User user = this.userService.findUserProfile(token);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String query) {
        logger.info("Searching for users with query: " + query);
        List<User> users = this.userService.searchUser(query);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserHandler(@RequestBody UpdateUserRequest request, @RequestHeader("Authorization") String token) throws UserException {
        logger.info("Received update request: " + request.toString());
        User user = this.userService.findUserProfile(token);
        this.userService.updateUser(user.getId(), request);

        ApiResponse response = new ApiResponse();
        response.setMessage("User details updated successfully.");
        response.setStatus(true);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.ACCEPTED);
    }
}
