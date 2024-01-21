package com.rakesh.sampleproject.Repository;

import com.rakesh.sampleproject.Entity.UserDetails;
import com.rakesh.sampleproject.Response.UserDetailsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    UserDetails findByMobileNumber(String mobileNumber);
}
