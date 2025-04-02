/*
 * Copyright (C) 2011-present ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary
 * information of iBOXCHAIN Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with iBOXCHAIN inc.
 */
package org.williams.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 类描述
 *
 * @author Williams
 * @since 2025-04-02
 */

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class MockTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        System.out.println("before");
    }

    @After
    public void tearDown() {
        System.out.println("after");
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/test/williams"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"response\":{\"requestId\":\"\",\"body\":\"1\"}}"));
    }
}
