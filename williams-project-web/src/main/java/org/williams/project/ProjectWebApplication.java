package org.williams.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@MapperScan("org.williams.project.**.mapper")
@SpringBootApplication
public class ProjectWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectWebApplication.class, args);
//        List<byte[]> me = new ArrayList<>();
//        while (true){
//            byte[] a = new byte[1024*1024];
//            me.add(a);
//            System.out.println(me.size());
//        }
    }

}
