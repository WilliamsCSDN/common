package org.williams.project.modules.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.openjdk.jmh.annotations.*;
import org.springframework.stereotype.Service;
import org.williams.project.modules.student.entity.Student;
import org.williams.project.modules.student.mapper.StudentMapper;
import org.williams.project.modules.student.service.StudentService;

@Service
@State(Scope.Benchmark)
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public int getStudent() {
//        log.info("hah williams hah");
        return 0;
    }
}
