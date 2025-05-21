package org.williams.project;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.williams.project.modules.machine.service.MachineService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProjectWebApplication.class)
class ProjectWebApplicationTests {

    @Autowired
    private MachineService orderStateMachine;

    @Test
    void contextLoads() throws Exception {
        orderStateMachine.payOrder("6333");
    }

}
