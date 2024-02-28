package com.example.qrcode.service.impl;

import com.example.qrcode.dto.QrReadDto;
import com.example.qrcode.model.Device;
import com.example.qrcode.model.UserDetails;
import com.example.qrcode.repository.DeviceRepository;
import com.example.qrcode.repository.UserDetailsRepository;
import com.example.qrcode.repository.service.UserDetailsRepoService;
import com.example.qrcode.service.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsRepoService userDetailsRepoService;
    private final DeviceRepository deviceRepository;

    public ServiceImpl(UserDetailsRepository userDetailsRepository, DeviceRepository deviceRepository, UserDetailsRepoService userDetailsRepoService) {
        this.userDetailsRepository = userDetailsRepository;
        this.deviceRepository = deviceRepository;
        this.userDetailsRepoService = userDetailsRepoService;
    }

    @Override
    public byte[] generateQrService(String text) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        byte[] qr;
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", FileSystems.getDefault().getPath("./src/main/resources/qrcodes/qr.png"));
            qr = out.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }

        return qr;
    }

    @Override
    public void saveUserService(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    @Override
    public UserDetails connectionService(QrReadDto qrReadDto) {
        String deviceId = qrReadDto.getDeviceId();
        UserDetails userDetail = userDetailsRepoService.findByMobileNumber(qrReadDto.getMobileNumber());
        Device device = new Device();
        device.setDeviceId(deviceId);
        device.setUser(userDetail);
        device.setStatus("active");
        deviceRepository.save(device);
        return userDetail;
    }
}
