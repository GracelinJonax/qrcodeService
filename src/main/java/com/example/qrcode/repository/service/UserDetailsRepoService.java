package com.example.qrcode.repository.service;

import com.example.qrcode.model.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailsRepoService {
    UserDetails findByMobileNumber(String mobileNumber);
}
