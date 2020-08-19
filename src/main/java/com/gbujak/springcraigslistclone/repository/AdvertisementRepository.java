package com.gbujak.springcraigslistclone.repository;

import com.gbujak.springcraigslistclone.model.Advertisement;
import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdvertisementRepository extends PagingAndSortingRepository<Advertisement, Long> {
    List<Advertisement> findByCategory(AdvertisementCategory category);
}
