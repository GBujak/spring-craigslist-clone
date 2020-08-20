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

    private String title;

    @OneToMany
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE}) // Delete images on advertisement deletion
    private Set<Image> images;

    // Safe HTML ad content generated from markdown written by user
    private String htmlContent;

    // Auto set to current date
    private Date date = new Date(new java.util.Date().getTime());

    private AdvertisementCategory category;

    // TODO: Change username to user foreign key ?
    private String userName;
}
