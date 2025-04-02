package org.williams.project.web;

import com.williams.plugins.annotation.RedisLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.williams.project.modules.student.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    private final StudentService studentService;

    @RequestMapping("/williams")
//@RedisLock(keyword = "", expireTime = 10L)
    public int test(){
        List<byte[]> me = new ArrayList<>();
        while (true){
            byte[] a = new byte[1024*1024];
            me.add(a);
            System.out.println(me.size());
        }
//        try {
//            Thread.sleep(100L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }


}
