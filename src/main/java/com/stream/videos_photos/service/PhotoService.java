package com.stream.videos_photos.service;


import com.stream.videos_photos.model.dto.response.PhotoUploadResponse;
import com.stream.videos_photos.model.repository.PhotoLogRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class PhotoService {

    private final PhotoLogRepository photoLogRepository;
    private final MinioClient minioClient;


    public PhotoService(PhotoLogRepository photoLogRepository, MinioClient minioClient) {
        this.photoLogRepository = photoLogRepository;
        this.minioClient = minioClient;
    }

    @Value("${minio.bucketName}")
    private String bucketName;


    public PhotoUploadResponse uploadPhoto(MultipartFile file){
        try {
            String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .stream(file.getInputStream(), file.getSize() ,-1)
                            .contentType(file.getContentType())
                            .build()

            );

            return new PhotoUploadResponse(filename);

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
