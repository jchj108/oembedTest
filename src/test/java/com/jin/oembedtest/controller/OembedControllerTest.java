package com.jin.oembedtest.controller;

import com.jin.oembedtest.domain.OembedProviderUrlList;
import com.jin.oembedtest.dto.Oembed;
import com.jin.oembedtest.service.OembedService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class OembedControllerTest {
    @InjectMocks
    private OembedService oembedService;

    @InjectMocks
    OembedProviderUrlList oembedUrlList;

    @InjectMocks
    OembedProviderUrlList oembedProviderUrlList;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    MockMvc mvc;


    @Test
    public void parsingOembedSuccess() throws Exception {


    }

    @Test
    public void parsingOembedFail() throws Exception {
        // Given
        Oembed oembed = new Oembed();
        oembed.setUrl("invalidURL");



//        oembedService.parsingOembed(oembed, urlList, model);


    }


}
