package com.example.qrcode.service;

import com.example.qrcode.dto.QrReadDto;
import com.example.qrcode.model.UserDetails;

@org.springframework.stereotype.Service
public interface Service {
    byte[] generateQrService(String text);

    void saveUserService(UserDetails userDetails);

    UserDetails connectionService(QrReadDto qrReadDto);
}
