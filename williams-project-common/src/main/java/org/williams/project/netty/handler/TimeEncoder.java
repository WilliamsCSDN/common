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
//package org.williams.project.netty.handler;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelOutboundHandlerAdapter;
//import io.netty.channel.ChannelPromise;
//import io.netty.handler.codec.MessageToByteEncoder;
//import org.williams.project.netty.pojo.UnixTime;
//
///**
// * 类描述
// *
// * @author Williams
// * @since 2025-03-05
// */
//
//public class TimeEncoder extends MessageToByteEncoder<UnixTime> {
//
////    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
////        UnixTime m = (UnixTime) msg;
////        ByteBuf b=  ctx.alloc().buffer(4);
////        b.writeInt((int)m.value());
////        ctx.write(b, promise);
////    }
//
//    @Override
//    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) throws Exception {
//        out.writeInt((int)msg.value());
//    }
//}
