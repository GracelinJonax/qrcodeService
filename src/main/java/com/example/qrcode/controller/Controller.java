package com.example.qrcode.controller;

import com.example.qrcode.dto.QrReadDto;
import com.example.qrcode.model.UserDetails;
import com.example.qrcode.service.Service;
import com.example.qrcode.api.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Controller implements Api {
    public final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<byte[]> generateQr() {
        String uuid = UUID.randomUUID().toString();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(service.generateQrService(uuid));
    }

    @Override
    public void saveUser(UserDetails userDetails) {
        service.saveUserService(userDetails);
    }

    @Override
    public ResponseEntity<UserDetails> connection(QrReadDto qrReadDto) {
        return new ResponseEntity<>(service.connectionService(qrReadDto), HttpStatus.OK);
    }
}
