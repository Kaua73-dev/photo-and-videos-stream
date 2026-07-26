package com.stream.videos_photos.model.repository;


import com.stream.videos_photos.model.entity.PhotoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoLogRepository extends JpaRepository<PhotoLog, Integer> {
}
