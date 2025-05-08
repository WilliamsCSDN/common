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
package org.williams.project.stream;

import cn.hutool.core.io.IoUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 类描述
 *
 * @author Williams
 * @since 2025-05-08
 */

public class FileStream {

    public static void main(String[] args) {


        // 写入
        try {
            File file = new File("F://1.txt");
            FileOutputStream fi = new FileOutputStream(file);
//            IoUtil.write(fi,StandardCharsets.UTF_8,false, "Williams123");

            fi.write("williams".getBytes(StandardCharsets.UTF_8));
            fi.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // 读取
        File file = new File("F://1.txt");
        try (FileInputStream fi = new FileInputStream(file)){
            byte[] a= new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while((len = fi.read(a)) != -1){
                sb.append(new String(a, 0, len));
            }

            System.out.println(sb);



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
