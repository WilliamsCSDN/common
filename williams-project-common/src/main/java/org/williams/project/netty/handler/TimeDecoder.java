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
//import io.netty.handler.codec.ByteToMessageDecoder;
//import org.williams.project.netty.pojo.UnixTime;
//
//import java.util.List;
//
///**
// * 类描述
// *
// * @author Williams
// * @since 2025-03-05
// */
//
//public class TimeDecoder extends ByteToMessageDecoder {
//    @Override
//    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//        if (byteBuf.readableBytes() < 4) {
//            return;
//        }
//
//        list.add(new UnixTime(byteBuf.readUnsignedInt()));
//    }
//}
