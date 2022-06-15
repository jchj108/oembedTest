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

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(OembedController.class)
                .build();
    }


    @Test
    public void homeTest() throws Exception {
        mockMvc.perform(
                get("/") // 넣어준 컨트롤러의 Http Method 와 URL 을 지정
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())		// 예상 응답
                .andDo(print())					// 호출 결과 로깅
                .andReturn();
    }

    @Test
    public void parsingOembedFail400() throws Exception {
        // Given
        String url = "test";
        // then
        mockMvc.perform(
                get("/oembed/parsingOembed")
                        .param("url", url))
                .andExpect(status().isBadRequest()
                );
    }
}
