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
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import org.williams.project.netty.handler.DiscardServerHandler;
//import org.williams.project.netty.handler.TimeEncoder;
//
///**
// * 类描述
// *
// * @author Williams
// * @since 2025-03-04
// */
//
//public class DiscardServer {
//    private int port;
//
//    public DiscardServer(int port) {
//        this.port = port;
//    }
//
//    public void run() throws Exception {
//        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            ServerBootstrap b = new ServerBootstrap(); // (2)
//            b.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class) // (3)
//                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
//                        @Override
//                        public void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast( new TimeEncoder(),new DiscardServerHandler());
//                        }
//                    })
//                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
//                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
//
//            // Bind and start to accept incoming connections.
//            ChannelFuture f = b.bind(port).sync(); // (7)
//
//            // Wait until the server socket is closed.
//            // In this example, this does not happen, but you can do that to gracefully
//            // shut down your server.
//            f.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        int port = 8081;
//        if (args.length > 0) {
//            port = Integer.parseInt(args[0]);
//        }
//
//        new DiscardServer(port).run();
//    }
//}
