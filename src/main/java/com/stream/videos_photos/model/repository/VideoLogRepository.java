package com.stream.videos_photos.model.repository;


import com.stream.videos_photos.model.entity.VideoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoLogRepository extends JpaRepository<VideoLog, Integer> {
}
