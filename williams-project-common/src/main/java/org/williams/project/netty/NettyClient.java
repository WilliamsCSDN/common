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
//package org.williams.project.netty;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import org.williams.project.netty.handler.TimeClientHandler;
//import org.williams.project.netty.handler.TimeDecoder;
//import org.williams.project.netty.handler.TimeDecoder2;
//
///**
// * 类描述
// *
// * @author Williams
// * @since 2025-03-05
// */
//
//public class NettyClient {
//
//    public static void main(String[] args) {
//        String host = "localhost";
//        int port = 8081;
//
//        EventLoopGroup work = new NioEventLoopGroup();
//        try {
//            Bootstrap b=  new Bootstrap();
//            b.group(work);
//
//            b.channel(NioSocketChannel.class);
//            b.option(ChannelOption.SO_KEEPALIVE, true);
//
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel socketChannel) throws Exception {
//                    socketChannel.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
//                }
//            });
//
//            ChannelFuture f = b.connect(host, port).sync();
//            f.channel().closeFuture().sync();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            work.shutdownGracefully();
//        }
//    }
//}
