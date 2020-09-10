package com.gbujak.springcraigslistclone.repository;

import com.gbujak.springcraigslistclone.model.Advertisement;
import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdvertisementRepository extends PagingAndSortingRepository<Advertisement, Long> {
    Page<Advertisement> findByCategoryOrderByDateDesc(AdvertisementCategory category, Pageable pageable);
    Long countByCategory(AdvertisementCategory category);
    List<Advertisement> findByUserName(String username);
}
