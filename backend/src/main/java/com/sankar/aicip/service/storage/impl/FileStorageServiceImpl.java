package com.sankar.aicip.service.storage.impl;

import com.sankar.aicip.service.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String storeFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Please select a file.");
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                (!contentType.equals("image/jpeg")
                        && !contentType.equals("image/png"))) {

            throw new RuntimeException(
                    "Only JPG and PNG images are allowed.");
        }
        long maxSize = 5 * 1024 * 1024; // 5 MB

        if (file.getSize() > maxSize) {
            throw new RuntimeException(
                    "Maximum upload size is 5 MB.");
        }

        try {

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFilename =
                    file.getOriginalFilename();

            String fileExtension = "";

            if (originalFilename != null &&
                    originalFilename.contains(".")) {

                fileExtension =
                        originalFilename.substring(
                                originalFilename.lastIndexOf("."));
            }

            String storedFilename =
                    UUID.randomUUID() + fileExtension;

            Path targetLocation =
                    uploadPath.resolve(storedFilename);

            Files.copy(
                    file.getInputStream(),
                    targetLocation,
                    StandardCopyOption.REPLACE_EXISTING);

            return storedFilename;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to store file.", e);
        }
    }
}