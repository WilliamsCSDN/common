package org.williams.project.modules.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.williams.project.modules.student.entity.Student;

public interface StudentService extends IService<Student> {

    int getStudent();
}
