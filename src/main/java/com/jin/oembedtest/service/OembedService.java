package com.jin.oembedtest.service;

import com.jin.oembedtest.dto.Oembed;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface OembedService {

    Map<String,Oembed> parsingOembed(Oembed oembed, List<String> urlList) throws Exception;
    Oembed CallOembed(String url);
}
