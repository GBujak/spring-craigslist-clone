package com.gbujak.springcraigslistclone.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    private String fileUrl;

    //  TODO: taking the path from application.properties doesn't work
    //  @Value("${imageStorageDirectory}")
    private static String imageDirectory = "/home/gbujak/Pictures/spring_pics";

    public static Set<Image> fromMultiparts(MultipartFile[] multipartFiles) {
        var set = new HashSet<Image>();
        for (var multipartFile : multipartFiles) {
            var filename = Paths.get(
                    UUID.randomUUID().toString() + "_" +
                    multipartFile.getOriginalFilename());
            var filepath = Paths.get(imageDirectory, filename.toString());
            try {
                multipartFile.transferTo(filepath);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            var image = new Image();
            image.setFileUrl(filename.toString());
            set.add(image);
        }
        return set;
    }
}
