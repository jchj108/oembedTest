package com.jin.oembedtest.controller;

import com.jin.oembedtest.domain.OembedProviderUrlList;
import com.jin.oembedtest.dto.Oembed;
import com.jin.oembedtest.service.OembedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ExceptionHandler(StringIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleStringIndexOutOfBoundsException(StringIndexOutOfBoundsException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/oembed/parsingOembed")
    public Map<String, Oembed> parsingOembed(HttpServletRequest request, @ModelAttribute Oembed oembed) throws Exception {

        List<String> urlList = oembedUrlList.getUrlList();
        Map<String, Oembed> url = oembedService.parsingOembed(oembed, urlList);

        return url;
    }
}
