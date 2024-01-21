package com.rakesh.sampleproject.Controller;

import com.rakesh.sampleproject.Constants.Constants;
import com.rakesh.sampleproject.Entity.UserDetails;
import com.rakesh.sampleproject.Request.UserInfoRequest;
import com.rakesh.sampleproject.Response.UserDetailsResponse;
import com.rakesh.sampleproject.Response.UserInfoResponse;
import com.rakesh.sampleproject.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserControllerImpl implements UserController {

    @Autowired
    public UserService service;

    public ResponseEntity<List<UserDetails>> getAllUsers() {
        return ResponseEntity.ok().body(service.getAllUserDetails());
    }

    public ResponseEntity<UserInfoResponse> createUser(@RequestBody @Valid UserInfoRequest request) throws Exception {
        UserInfoResponse response = service.addUserInfo(request);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    public ResponseEntity<UserDetailsResponse> getUserDetailsByMobileNumber(@PathVariable String mobileNumber) {
        UserDetailsResponse response = service.getUserDetails(mobileNumber);
        if (response == null || response.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!!");
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<UserInfoResponse> updateUserDetails(UserInfoRequest request) throws Exception {
        UserInfoResponse response = service.updateUserInfo(request);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put("status", Constants.BAD_REQUEST);
            String fieldName = "message";
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
