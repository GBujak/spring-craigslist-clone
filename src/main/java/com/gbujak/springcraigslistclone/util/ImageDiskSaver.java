package com.gbujak.springcraigslistclone.util;

import com.gbujak.springcraigslistclone.model.Image;
import com.gbujak.springcraigslistclone.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ImageDiskSaver {
    private ImageService imageService;

    public ImageDiskSaver(ImageService imageService) {
        this.imageService = imageService;
    }

    @Value("${imageStorageDirectory}")
    private String imageDirectory; // = "/home/gbujak/Pictures/spring_pics";

    public List<Image> fromMultiparts(MultipartFile[] multipartFiles) {
        var list = new ArrayList<Image>();
        if (multipartFiles == null) return list;
        for (var multipartFile : multipartFiles) {
            if (multipartFile.getOriginalFilename() == null
                    || multipartFile.getOriginalFilename().equals("")) continue;
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
            list.add(image);
        }
        return list;
    }

    public List<Image> fromMultipartsSaveToService(MultipartFile[] files) {
        var images = fromMultiparts(files);
        return images.stream()
                .map(it -> imageService.save(it))
                .collect(Collectors.toList());
    }

    public boolean delete(String filename) {
        try {
            var file = new File(Paths.get(imageDirectory, filename).toString());
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }
}
