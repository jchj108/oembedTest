package com.jin.oembedtest.controller;

import com.jin.oembedtest.domain.OembedProviderUrlList;
import com.jin.oembedtest.dto.Oembed;
import com.jin.oembedtest.service.OembedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class OembedController {

    @Autowired
    OembedService oembedService;

    @Autowired
    OembedProviderUrlList oembedUrlList;

    @RequestMapping("/")
    public String home() {
        return "/index";
    }

    @ResponseBody
    @RequestMapping("/oembed/parsingOembed")
    public Map<String, Oembed> parsingOembed(HttpServletRequest request, @ModelAttribute Oembed oembed) throws Exception {

        List<String> urlList = oembedUrlList.getUrlList();
        Map<String, Oembed> url = oembedService.parsingOembed(oembed, urlList);

        return url;
    }
}
