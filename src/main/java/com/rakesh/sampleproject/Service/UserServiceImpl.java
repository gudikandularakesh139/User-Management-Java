package com.rakesh.sampleproject.Service;

import com.rakesh.sampleproject.Constants.Constants;
import com.rakesh.sampleproject.Entity.UserDetails;
import com.rakesh.sampleproject.Repository.UserDetailsRepository;
import com.rakesh.sampleproject.Request.UserInfoRequest;
import com.rakesh.sampleproject.Response.UserDetailsResponse;
import com.rakesh.sampleproject.Response.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserInfoResponse addUserInfo(UserInfoRequest userInfoRequest) throws Exception {
        UserInfoResponse response = new UserInfoResponse();
        UserDetails userDetails = new UserDetails();
        UserDetails existingUser = null;
        try {
            if (userInfoRequest != null) {
                if (userInfoRequest.getMobileNumber() != null) {
                    existingUser = userDetailsRepository.findByMobileNumber(userInfoRequest.getMobileNumber());
                }
                if (existingUser == null) {
                    userDetails.setFirstName(userInfoRequest.getFirstName());
                    userDetails.setLastName(userInfoRequest.getLastName());
                    userDetails.setEmailId(userInfoRequest.getEmailId());
                    userDetails.setMobileNumber(userInfoRequest.getMobileNumber());
                    userDetails.setAddress(userInfoRequest.getAddress());
                    userDetailsRepository.save(userDetails);
                    response.setStatus(Constants.SUCESS_CODE);
                    response.setMessage(Constants.USER_DATA_SAVED_SUCCESSFULLY);
                } else {
                    response.setStatus(Constants.FAILURE_CODE);
                    response.setMessage(Constants.USER_ENROLLED_ALREADY);
                }
            } else {
                response.setStatus(Constants.FAILURE_CODE);
                response.setMessage(Constants.REQUEST_FIELDS_CANNOT_BE_EMPTY_OR_NULL);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
        return response;
    }

    @Override
    public UserDetailsResponse getUserDetails(String mobileNumber) {
        UserDetailsResponse response = new UserDetailsResponse();
        if (mobileNumber != null) {
            UserDetails userDetails = userDetailsRepository.findByMobileNumber(mobileNumber);
            if (userDetails != null && userDetails.getId() != null) {
                response.setId(userDetails.getId());
                response.setFirstName(userDetails.getFirstName());
                response.setLastName(userDetails.getLastName());
                response.setEmailId(userDetails.getEmailId());
                response.setAddress(userDetails.getAddress());
                response.setMobileNumber(userDetails.getMobileNumber());
            }
        }
        return response;
    }

    @Override
    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    @Override
    public UserInfoResponse updateUserInfo(UserInfoRequest userInfoRequest) throws Exception {
        UserInfoResponse response = new UserInfoResponse();
        UserDetails userDetails = new UserDetails();
        UserDetails existingUser = null;
        try {
            if (userInfoRequest.getMobileNumber() != null) {
                existingUser = userDetailsRepository.findByMobileNumber(userInfoRequest.getMobileNumber());
            }
            if (existingUser == null) {
                response.setStatus(Constants.NOT_FOUND);
                response.setMessage(Constants.USER_NOT_FOUND);
            } else {
                userDetails.setId(existingUser.getId());
                userDetails.setFirstName(userInfoRequest.getFirstName());
                userDetails.setLastName(userInfoRequest.getLastName());
                userDetails.setEmailId(userInfoRequest.getEmailId());
                userDetails.setMobileNumber(userInfoRequest.getMobileNumber());
                userDetails.setAddress(userInfoRequest.getAddress());
                userDetailsRepository.save(userDetails);
                response.setStatus(Constants.SUCESS_CODE);
                response.setMessage(Constants.USER_DATA_UPDATED_SUCCESSFULLY);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
        return response;
    }
}
