package com.gbujak.springcraigslistclone.util;

import com.gbujak.springcraigslistclone.model.Image;
import com.gbujak.springcraigslistclone.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ImageDiskSaver {
    private ImageService imageService;

    public ImageDiskSaver(ImageService imageService) {
        this.imageService = imageService;
    }

    @Value("${imageStorageDirectory}")
    private String imageDirectory; // = "/home/gbujak/Pictures/spring_pics";

    public Set<Image> fromMultiparts(MultipartFile[] multipartFiles) {
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

    public Set<Image> fromMultipartsSaveToService(MultipartFile[] files) {
        var images = fromMultiparts(files);
        return images.stream()
                .map(it -> imageService.save(it))
                .collect(Collectors.toSet());
    }
}