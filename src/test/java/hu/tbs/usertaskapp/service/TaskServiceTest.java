package hu.tbs.usertaskapp.service;

import hu.tbs.usertaskapp.mapper.TaskMapper;
import hu.tbs.usertaskapp.repository.TaskRepository;
import hu.tbs.usertaskapp.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Spy
    private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    @Test
    public void getAllTasksForUserTest() {
    }

    @Test
    public void getTaskForUserTest() {
    }

    @Test
    public void createTaskTest() {
    }

    @Test
    public void updateTaskTest() {
    }

    @Test
    public void deleteTaskTest() {
    }

    @Test
    public void checkIfTasksHadExpiredTest() {
    }
}
