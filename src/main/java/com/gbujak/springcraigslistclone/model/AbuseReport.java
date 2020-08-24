package com.gbujak.springcraigslistclone.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class AbuseReport {
    @Id
    @GeneratedValue
    private Long id;
    private String description;

    @ManyToOne
    private Advertisement advertisement;
}
