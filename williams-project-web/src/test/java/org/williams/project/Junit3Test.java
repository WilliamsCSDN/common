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

import com.google.common.util.concurrent.RateLimiter;
import junit.framework.TestCase;

import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

/**
 * 类描述
 *
 * @author Williams
 * @since 2025-05-09
 */
public class Junit3Test extends TestCase {

    public void testHa() throws InterruptedException {
        System.out.println("sdaf");

        RateLimiter r= RateLimiter.create(1);

        CountDownLatch c = new CountDownLatch(3);

        new Thread(() ->{
            System.out.println(r.acquire());
            c.countDown();

        }).start();


        new Thread(() ->{
            System.out.println(r.acquire());
            c.countDown();

        }).start();

        new Thread(() ->{
            System.out.println(r.acquire());
            c.countDown();

        }).start();

        c.await();
    }

    public void testF (){
        function(a ->{
            System.out.println(a.toString());
            return a.toString();
        });

    }


    public void function(Function<Integer, String> a){
        a.apply(1);
    }
}
