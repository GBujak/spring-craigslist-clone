package com.gbujak.springcraigslistclone.service;

import com.gbujak.springcraigslistclone.model.AbuseReport;
import com.gbujak.springcraigslistclone.model.Advertisement;
import com.gbujak.springcraigslistclone.repository.AbuseReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbuseReportService {
    private AbuseReportRepository abuseReportRepository;

    public AbuseReportService(AbuseReportRepository abuseReportRepository) {
        this.abuseReportRepository = abuseReportRepository;
    }

    public List<AbuseReport> findByAdvertisement(Advertisement advertisement) {
        return abuseReportRepository.findByAdvertisement(advertisement);
    }

    public AbuseReport save(AbuseReport abuseReport) {
        return abuseReportRepository.save(abuseReport);
    }
}
