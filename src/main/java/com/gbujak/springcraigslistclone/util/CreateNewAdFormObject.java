package com.gbujak.springcraigslistclone.util;

import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateNewAdFormObject {
    private String title;
    private String content;
    private AdvertisementCategory category;
    private MultipartFile[] images;
}
