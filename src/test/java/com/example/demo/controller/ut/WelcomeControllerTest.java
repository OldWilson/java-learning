package com.example.demo.controller.ut;

import com.example.demo.contants.Common;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getWelcome() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/welcome").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("welcome to docker")));
    }

    @Test
    public void test001() {
        String str = "１２３４５６７８９　０";
        for (Map.Entry<String, String> entry : Common.FULL_ANGLE_DIGITAL.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (str.contains(key)) {
                str = str.replace(key, value);
            }
        }
        System.out.println(str);
        System.out.println();
    }
}
