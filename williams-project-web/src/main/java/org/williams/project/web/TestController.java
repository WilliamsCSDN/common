package org.williams.project.web;

import com.williams.plugins.annotation.RedisLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.williams.project.modules.machine.service.MachineService;
import org.williams.project.modules.student.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    private final StudentService studentService;

    private final MachineService machineService;

    @RequestMapping("/williams")
//@RedisLock(keyword = "", expireTime = 10L)
    public String test(){
//        List<byte[]> me = new ArrayList<>();
//        while (true){
//            byte[] a = new byte[1024*1024];
//            me.add(a);
//            System.out.println(me.size());
//        }

//        machineService.createOrder();
        System.out.println("haa");
        return "1";

//        try {
//            Thread.sleep(100L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    @GetMapping("/create")
    public String create() throws Exception {
        machineService.createOrder("666");
        return "1";
    }

    @GetMapping("/payOrder")
    public String payOrder() throws Exception {
        machineService.payOrder("666");
        return "1";
    }

    @GetMapping("/paySuccess")
    public String paySuccess() throws Exception {
        machineService.handlePaymentResult("666", true);
        return "1";
    }


}
