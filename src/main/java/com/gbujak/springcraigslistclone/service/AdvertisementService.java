package com.gbujak.springcraigslistclone.service;

import com.gbujak.springcraigslistclone.model.Advertisement;
import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import com.gbujak.springcraigslistclone.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementService {
    private final AdvertisementRepository adRepository;

    public AdvertisementService(AdvertisementRepository adRepository) {
        this.adRepository = adRepository;
    }

    public List<Advertisement> findByCategory(AdvertisementCategory category) {
        return adRepository.findByCategory(category);
    }

    public Optional<Advertisement> findById(Long id) {
        return adRepository.findById(id);
    }

    public Advertisement save(Advertisement ad) {
        return adRepository.save(ad);
    }
}
