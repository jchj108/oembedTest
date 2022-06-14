package com.jin.oembedtest.dto;

import lombok.*;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class Oembed {

    private String url;
    private String title;
    private String author_url;
    private String type;
    private int height;
    private int width;
    private String version;
    private String provider_name;
    private int thumbnail_height;
    private int thumbnail_width;
    private String thumbnail_url;
    private String html;
    private String errorMsg;

}
