package com.stream.videos_photos.service;


import com.stream.videos_photos.model.repository.PhotoLogRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class PhotoService {

    private final PhotoLogRepository photoLogRepository;
    private final MinioClient minioClient;


    public PhotoService(PhotoLogRepository photoLogRepository, MinioClient minioClient) {
        this.photoLogRepository = photoLogRepository;
        this.minioClient = minioClient;
    }

    @Value("${minio.bucket-name}")
    private String bucketName;


    public String uploadPhoto(MultipartFile file){
        try {
            String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .stream(file.getInputStream(), file.getSize() ,-1)
                            .contentType(file.getContentType())
                            .build()

            );

            return filename;

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
