package com.QR_generator.QR_Code_Generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QRCodeGenerator {
    public static void main(String[] args) {
        String userId = "101";
        String userName = "Manjeet";
        String address = "123 Main Street";
        String email = "manjeet@gmail.com";
        String phone = "123456789";

        String userData = "User Id: " + userId + "\n"
                + "User Name: " + userName + "\n"
                + "User Address: " + address + "\n"
                + "User Email: " + email + "\n"
                + "User Phone: " + phone;

        String dirPath = "qrcode";
        String filePath = dirPath + File.separator + "user_qr_code.png";

        Path dir = FileSystems.getDefault().getPath(dirPath);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
                System.out.println("Directory created");
            } catch (IOException e) {
                e.printStackTrace();
            }

            Map<EncodeHintType, Object> hintMap = new HashMap<>();
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            try {
                BitMatrix bitMatrix = new MultiFormatWriter()
                        .encode(userData, BarcodeFormat.QR_CODE, 300, 300, hintMap);
                MatrixToImageWriter.writeToStream(bitMatrix, "PNG", new FileOutputStream(filePath));
                System.out.println("QR code generated");
            } catch (IOException | WriterException e) {
                System.out.println("Error generating qr code: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
