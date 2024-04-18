package org.williams.project.modules.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.williams.project.modules.student.entity.Student;
import org.williams.project.modules.student.mapper.StudentMapper;
import org.williams.project.modules.student.service.StudentService;

@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public int getStudent() {
        log.info("hah williams hah");
        return 0;
    }
}
