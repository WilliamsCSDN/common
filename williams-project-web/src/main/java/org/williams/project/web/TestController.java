package org.williams.project.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.williams.project.modules.student.service.StudentService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    private final StudentService studentService;

    @RequestMapping("/williams")
    public int test(){
        return studentService.getStudent();
    }


}
