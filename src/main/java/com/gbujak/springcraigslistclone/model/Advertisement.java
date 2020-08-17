package com.gbujak.springcraigslistclone.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
public class Advertisement {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE}) // Delete images on advertisement deletion
    private Set<Image> images;

    // Safe HTML ad content generated from markdown written by user
    private String advertisementSafeHtmlContent;

    private Date date;

    private AdvertisementCategory category;

    // TODO: Add user column
    //@ManyToOne
    //private ApplicationUser user;
}
