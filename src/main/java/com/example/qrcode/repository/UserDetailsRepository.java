package com.example.qrcode.repository;

import com.example.qrcode.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
    UserDetails findByMobileNumber(String mobileNumber);
}
