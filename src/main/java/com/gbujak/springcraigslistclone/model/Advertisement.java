package com.gbujak.springcraigslistclone.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Advertisement {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(
            mappedBy = "advertisement", cascade = CascadeType.ALL
    )
    private List<Image> images;

    // Safe HTML ad content generated from markdown written by user
    private String htmlContent;

    // Auto set to current date
    private Date date = new Date(new java.util.Date().getTime());

    private AdvertisementCategory category;

    // TODO: Change username to user foreign key ?
    private String userName;

    @OneToMany(
            mappedBy = "advertisement", cascade = CascadeType.ALL
    )
    private List<AbuseReport> abuseReports;
}
