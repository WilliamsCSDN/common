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
//package org.williams.project.sentinel;
//
//import com.alibaba.csp.sentinel.EntryType;
//import com.alibaba.csp.sentinel.ResourceTypeConstants;
//import com.alibaba.csp.sentinel.adapter.reactor.EntryConfig;
//import com.alibaba.csp.sentinel.adapter.reactor.SentinelReactorTransformer;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import reactor.core.publisher.Mono;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//
///**
// * 类描述
// *
// * @author Williams
// * @since 2025-04-29
// */
//
//public class SentinelTest {
//
//    public static void main(String[] args) {
//
//        init();
//
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        for (int i = 0; i < 1000; i++){
//            new Thread(() ->{
//                ReactorExample example = new ReactorExample();
//                Mono<String> result = example.doSomething();
//                result.subscribe(System.out::println);
//                countDownLatch.countDown();
//            }).start();
//        }
//
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//    private static void init(){
//        List<FlowRule> a= new ArrayList<>();
//        FlowRule b = new FlowRule();
//        b.setResource("mytest");
//        b.setGrade(1);
//        b.setCount(1);
//        a.add(b);
//        FlowRuleManager.loadRules(a);
//    }
//
//     static class ReactorExample {
//
//        public Mono<String> doSomething() {
//            return Mono.just("Hello, Sentinel!")
//                    .transform(new SentinelReactorTransformer<>(
//                            new EntryConfig("mytest1", ResourceTypeConstants.COMMON_API_GATEWAY, EntryType.IN, null)));
//        }
//    }
//}
