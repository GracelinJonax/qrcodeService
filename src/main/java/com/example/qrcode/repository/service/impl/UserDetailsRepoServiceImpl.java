package com.example.qrcode.repository.service.impl;

import com.example.qrcode.model.UserDetails;
import com.example.qrcode.repository.service.UserDetailsRepoService;
import com.example.qrcode.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsRepoServiceImpl implements UserDetailsRepoService {
    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsRepoServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails findByMobileNumber(String mobileNumber) {
        return userDetailsRepository.findByMobileNumber(mobileNumber);
    }
}
