package com.rakesh.sampleproject.Service;

import com.rakesh.sampleproject.Entity.UserDetails;
import com.rakesh.sampleproject.Request.UserInfoRequest;
import com.rakesh.sampleproject.Response.UserDetailsResponse;
import com.rakesh.sampleproject.Response.UserInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public UserInfoResponse addUserInfo(UserInfoRequest userInfoRequest) throws Exception;

    public UserDetailsResponse getUserDetails(String mobileNumber);

    public List<UserDetails> getAllUserDetails();

    public UserInfoResponse updateUserInfo(UserInfoRequest userInfoRequest) throws Exception;
}
