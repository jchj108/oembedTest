package com.jin.oembedtest.controller;

import com.jin.oembedtest.dto.Oembed;
import com.jin.oembedtest.service.OembedService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //need this in Spring Boot test
public class OembedControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void homeTest() throws Exception {
        mockMvc.perform(
                        get("/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void parsingOembedSuccess() throws Exception {
        // given
        String url = "https://www.youtube.com/watch?v=bJfbPWEMj_c&ab_channel=%EB%B0%B1%EA%B8%B0%EC%84%A0";
        // then
        mockMvc.perform(
                        get("/oembed/parsingOembed")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .param("url", url))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void parsingOembedFail400Npe() throws Exception { // BAD_REQUEST NPE
        // given
        String url = null;
        // then
        mockMvc.perform(
                        get("/oembed/parsingOembed")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .param("url", url))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void parsingOembedFail400() throws Exception { // BAD_REQUEST
        // given
        String url = "test";
        // then
        mockMvc.perform(
                        get("/oembed/parsingOembed")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .param("url", url))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void parsingOembedFail400ParsingError() throws Exception { // NOT_ACCEPTABLE
        // given
        String url = "test.test.test";
        // then
        mockMvc.perform(
                        get("/oembed/parsingOembed")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .param("url", url))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void parsingOembedFail500() throws Exception { // INTERNAL_SERVER_ERROR
        // given
        String url = "https://www.youtube.com/watch?v=asedzxcvzxcv";
        // then
        mockMvc.perform(
                        get("/oembed/parsingOembed")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .param("url", url))
                .andExpect(status().isInternalServerError())
                .andDo(print())
                .andReturn();
    }
}
