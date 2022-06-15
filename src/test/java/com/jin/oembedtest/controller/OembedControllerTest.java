package com.jin.oembedtest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
public class OembedControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void homeTest() throws Exception {
        mockMvc.perform(
                        get("/")) // 넣어준 컨트롤러의 Http Method 와 URL 을 지정
                        .andExpect(status().isOk()
        );

    }

    @Test
    public void parsingOembedFail() throws Exception {


//        oembedService.parsingOembed(oembed, urlList, model);


    }


}
