///*
// * Copyright (C) 2011-present ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
// *
// * All right reserved.
// *
// * This software is the confidential and proprietary
// * information of iBOXCHAIN Company of China.
// * ("Confidential Information"). You shall not disclose
// * such Confidential Information and shall use it only
// * in accordance with the terms of the contract agreement
// * you entered into with iBOXCHAIN inc.
// */
//package org.williams.project;
//
//import org.junit.Test;
//import reactor.core.publisher.Mono;
//import reactor.netty.http.server.HttpServer;
//
///**
// * 类描述
// *
// * @author Williams
// * @since 2025-05-13
// */
//
//public class WebFluxTest {
//
//    @Test
//    public void williamstest(){
//        try {
//            HttpServer.create()
//                    .host("127.0.0.1")
//                    .port(8080)
//                    .route(x -> x.get("/hello", (request, response) -> response.sendString(Mono.just("Hello World!"))))
//                    .bindNow().onDispose().block()
//                    ;
//
//
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
