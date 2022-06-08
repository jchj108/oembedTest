package com.jin.oembedtest.controller;

import com.jin.oembedtest.dto.Oembed;
import com.jin.oembedtest.service.OembedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OembedController {

//    private final OembedService oembedService;
//
//    public OembedController(OembedService oembedService) {
//        this.oembedService = oembedService;
//    }


    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/oembed/parsingOembed")
    public String parsingOembed(HttpServletRequest request, @ModelAttribute Oembed oembed) {

        System.out.println(oembed.getUrl());

        return "index";
    }




}
