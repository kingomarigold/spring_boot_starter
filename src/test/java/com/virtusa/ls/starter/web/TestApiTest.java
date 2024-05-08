package com.virtusa.ls.starter.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.ls.starter.dto.TestTableDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class TestApiTest {

    @Autowired
    MockMvc mockMvc;

    TestTableDto createRequestBody(String name) {
        TestTableDto retVal = new TestTableDto();
        retVal.setName(name);
        return retVal;
    }

    MvcResult addTestData(String name) throws Exception {
        TestTableDto req = createRequestBody(name);
        String content = new ObjectMapper().writeValueAsString(req);
        MvcResult res = this.mockMvc
                .perform(post("/api/test-table").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(content))
                .andDo(print()).andExpect(status().isCreated()).andReturn();
        return res;
    }

    @Test
    void testPost() throws JsonProcessingException, Exception {
        MvcResult res = addTestData("Yrliet Lanaevyss");

        String location = res.getResponse().getHeader("location");
        MvcResult getRes = this.mockMvc.perform(get(location).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        TestTableDto getDto = new ObjectMapper().readValue(getRes.getResponse().getContentAsString(),
                TestTableDto.class);
        assert getDto.getName().equals("Yrliet Lanaevyss");
    }

    @Test
    void testPostWithoutName() throws Exception {
        TestTableDto req = new TestTableDto();
        String content = new ObjectMapper().writeValueAsString(req);
        this.mockMvc
                .perform(post("/api/test-table").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(content))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void testGetForInvalidId() throws Exception {
        this.mockMvc.perform(get("/api/test-table/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void testGetAll() throws Exception {
        addTestData("Yrliet Lanaevyss");
        addTestData("Shadowheart");
        MvcResult res = this.mockMvc.perform(get("/api/test-table").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        TypeReference<List<TestTableDto>> typeReference = new TypeReference<List<TestTableDto>>() {// Empty
        };
        List<TestTableDto> values = new ObjectMapper().readValue(res.getResponse().getContentAsString(), typeReference);
        assert values.size() == 2;
        List<String> names = values.stream().map(value -> value.getName()).toList();
        assert names.contains("Yrliet Lanaevyss");
        assert names.contains("Shadowheart");
    }

}
