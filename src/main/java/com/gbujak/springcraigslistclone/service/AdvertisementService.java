package com.gbujak.springcraigslistclone.service;

import com.gbujak.springcraigslistclone.model.Advertisement;
import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import com.gbujak.springcraigslistclone.repository.AdvertisementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementService {
    private final AdvertisementRepository adRepository;

    public AdvertisementService(AdvertisementRepository adRepository) {
        this.adRepository = adRepository;
    }

    public Page<Advertisement> findByCategory(AdvertisementCategory category, int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        return adRepository.findByCategory(category, pageRequest);
    }

    public Optional<Advertisement> findById(Long id) {
        return adRepository.findById(id);
    }

    public Advertisement save(Advertisement ad) {
        return adRepository.save(ad);
    }

    public void delete(Advertisement advertisement) {
        adRepository.delete(advertisement);
    }
}
