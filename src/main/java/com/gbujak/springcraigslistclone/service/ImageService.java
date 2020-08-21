package com.gbujak.springcraigslistclone.service;

import com.gbujak.springcraigslistclone.model.Image;
import com.gbujak.springcraigslistclone.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
