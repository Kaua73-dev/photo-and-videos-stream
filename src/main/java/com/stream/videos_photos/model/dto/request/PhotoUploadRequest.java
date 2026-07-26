package com.stream.videos_photos.model.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record PhotoUploadRequest(MultipartFile multipartFile) {
}
