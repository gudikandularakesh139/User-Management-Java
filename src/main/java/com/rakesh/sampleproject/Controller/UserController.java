package com.rakesh.sampleproject.Controller;

import com.rakesh.sampleproject.Entity.UserDetails;
import com.rakesh.sampleproject.Request.UserInfoRequest;
import com.rakesh.sampleproject.Response.UserDetailsResponse;
import com.rakesh.sampleproject.Response.UserInfoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/v1/details/")
public interface UserController {

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDetails>> getAllUsers();

    @PostMapping("/createUser")
    public ResponseEntity<UserInfoResponse> createUser(@RequestBody @Valid UserInfoRequest request) throws Exception;

    @GetMapping("/getUser/{mobileNumber}")
    public ResponseEntity<UserDetailsResponse> getUserDetailsByMobileNumber(@PathVariable String mobileNumber);

    @PutMapping("/updateUser")
    public ResponseEntity<UserInfoResponse> updateUserDetails(@RequestBody @Valid UserInfoRequest request) throws Exception;
}
