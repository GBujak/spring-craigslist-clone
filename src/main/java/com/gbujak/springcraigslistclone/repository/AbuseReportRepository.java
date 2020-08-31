package com.gbujak.springcraigslistclone.repository;

import com.gbujak.springcraigslistclone.model.AbuseReport;
import com.gbujak.springcraigslistclone.model.Advertisement;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AbuseReportRepository extends PagingAndSortingRepository<AbuseReport, Long> {
    List<AbuseReport> findByAdvertisement(Advertisement advertisement);
    void deleteByAdvertisement(Advertisement advertisement);
}
