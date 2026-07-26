package com.stream.videos_photos.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "video_log")
public class VideoLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



}
