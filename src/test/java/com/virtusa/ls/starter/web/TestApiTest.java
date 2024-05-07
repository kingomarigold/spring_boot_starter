package com.virtusa.ls.starter.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.ls.starter.dto.TestTableDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TestApiTest {
    
    @Autowired
    MockMvc mockMvc;
    
    TestTableDto createRequestBody() {
        TestTableDto retVal = new TestTableDto();
        retVal.setName("Yrliet Lanaevyss");
        return retVal;
    }
    
    @Test
    void testPost() throws JsonProcessingException, Exception {
        TestTableDto req = createRequestBody();
        String content = new ObjectMapper().writeValueAsString(req);
        MvcResult res = this.mockMvc.perform(post("/api/test-table")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content))
        .andDo(print())
        .andExpect(status().isCreated())
        .andReturn();
        
        String location = res.getResponse().getHeader("location");
        MvcResult getRes = this.mockMvc.perform(get(location)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TestTableDto getDto = new ObjectMapper().readValue(getRes.getResponse().getContentAsString(), 
                TestTableDto.class);
        assert getDto.getName().equals(req.getName());
    }
}
