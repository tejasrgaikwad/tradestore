package com.db.tradestorage;

import com.db.tradestorage.model.Trade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeStoreApplication.class)
public class TradeStoreApiTest {
    private static final String BASE_URL="http://localhost:8080/api/trade";
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.objectMapper = new ObjectMapper();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    private MvcResult sendRequest(String trade) throws Exception {
        return mockMvc.perform(post(BASE_URL)
                        .contentType("application/json")
                        .content(trade))
                .andExpect(status().isOk()
                ).andReturn();
    }


    @Test
    public void addNewTrade() throws Exception {
        String requestBody="{\n" +
                "  \"bookId\": \"B3\",\n" +
                "  \"counterParty\": \"CP-3\",\n" +
                "  \"createdDate\": \"26/06/2022\",\n" +
                "  \"expiredFlag\": \"N\",\n" +
                "  \"maturityDate\": \"27/06/2022\",\n" +
                "  \"tradeId\": \"T3\",\n" +
                "  \"version\": 3\n" +
                "}";
        MvcResult mvcResult = sendRequest(requestBody);
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    
}
