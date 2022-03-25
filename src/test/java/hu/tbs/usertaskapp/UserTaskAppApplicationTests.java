package hu.tbs.usertaskapp;

import hu.tbs.usertaskapp.service.TaskService;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserTaskAppApplicationTests {

    @SpyBean
    private TaskService taskService;

    @Test
    public void checkIfTasksHadExpiredTest() {
        await()
                .atMost(Duration.FIVE_MINUTES)
                .untilAsserted(() -> verify(taskService, atLeast(1)).checkIfTasksHadExpired());
    }

}

