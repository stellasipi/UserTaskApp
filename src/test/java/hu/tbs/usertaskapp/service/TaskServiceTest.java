package hu.tbs.usertaskapp.service;

import hu.tbs.usertaskapp.mapper.TaskMapper;
import hu.tbs.usertaskapp.model.Task;
import hu.tbs.usertaskapp.model.User;
import hu.tbs.usertaskapp.model.dto.TaskCreateDTO;
import hu.tbs.usertaskapp.model.dto.TaskInfoDTO;
import hu.tbs.usertaskapp.repository.TaskRepository;
import hu.tbs.usertaskapp.repository.UserRepository;
import hu.tbs.usertaskapp.util.TaskStatus;
import hu.tbs.usertaskapp.util.exception.TaskException;
import hu.tbs.usertaskapp.util.exception.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.Suite;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Suite
public class TaskServiceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static List<User> testUsers;

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Spy
    private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    @BeforeAll
    static void setUpTests() {
        // Tasks for TestUser1
        Task tesUser1Task1 = Task.builder()
                .id(1)
                .name("Message delivery")
                .description("Deliver a message to Adam.")
                .dateTime(LocalDateTime.parse("2022-05-25 14:25:00", dateTimeFormatter))
                .status(TaskStatus.PENDING)
                .build();
        Task tesUser1Task2 = Task.builder()
                .id(2)
                .name("Book")
                .description("Read the book.")
                .dateTime(LocalDateTime.parse("2020-05-25 14:25:00", dateTimeFormatter))
                .status(TaskStatus.PENDING)
                .build();

        // TestUser1
        User testUser1 = User.builder()
                .id(1)
                .firstName("Jared")
                .lastName("Smith")
                .username("jared.smith")
                .tasks(Stream.of(tesUser1Task1, tesUser1Task2).toList())
                .build();

        // Tasks for TestUser2
        Task tesUser2Task1 = Task.builder()
                .id(3)
                .name("Study")
                .description("Study for the exam")
                .dateTime(LocalDateTime.parse("2023-05-25 14:25:00", dateTimeFormatter))
                .status(TaskStatus.PENDING)
                .build();
        Task tesUser2Task2 = Task.builder()
                .id(4)
                .name("Finance check")
                .description("Check my financial status")
                .dateTime(LocalDateTime.parse("2022-09-25 14:25:00", dateTimeFormatter))
                .status(TaskStatus.PENDING)
                .build();

        // TestUser2
        User testUser2 = User.builder()
                .id(2)
                .firstName("Frances")
                .lastName("Woods")
                .username("frances.woods")
                .tasks(Stream.of(tesUser2Task1, tesUser2Task2).toList())
                .build();

        // Tasks for TestUser3
        Task tesUser3Task1 = Task.builder()
                .id(5)
                .name("Painting")
                .description("Paint the picture to my friend")
                .dateTime(LocalDateTime.parse("2019-05-25 14:25:00", dateTimeFormatter))
                .status(TaskStatus.DONE)
                .build();

        Task tesUser3Task2 = Task.builder()
                .id(6)
                .name("Shopping")
                .description("Buy that candle")
                .dateTime(LocalDateTime.parse("2018-05-25 14:25:00", dateTimeFormatter))
                .status(TaskStatus.PENDING)
                .build();

        // TestUser3
        User testUser3 = User.builder()
                .id(3)
                .firstName("Danny")
                .lastName("west")
                .username("danny.west")
                .tasks(Stream.of(tesUser3Task1, tesUser3Task2).toList())
                .build();

        testUsers = Stream.of(testUser1, testUser2, testUser3).toList();
    }

    @Test
    public void getAllTasksForUserTest() {
        User testUser = testUsers.get(0);
        List<Task> testTasks = testUser.getTasks();

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(taskRepository.findByUser(testUser)).thenReturn(testTasks);

        try {
            List<TaskInfoDTO> tasksDTO = taskService.getAllTasksForUser(testUser.getId());
            List<Task> tasks = tasksDTO.stream().map(taskDTO -> taskMapper.taskInfoDtoToTask(taskDTO)).toList();

            Assertions.assertEquals(tasks.size(), testTasks.size());

            AtomicInteger count = new AtomicInteger();
            tasks.forEach(task -> {
                Assertions.assertEquals(testTasks.get(count.get()).getName(), task.getName());
                Assertions.assertEquals(testTasks.get(count.get()).getDescription(), task.getDescription());
                Assertions.assertEquals(testTasks.get(count.getAndIncrement()).getDateTime(), task.getDateTime());
            });
        } catch (UserException e) {
            System.out.println("UserException thrown: " + e.getMessage());
        }

    }

    @Test
    public void getTaskForUserTest() {
        User testUser = testUsers.get(1);
        Task testTask = testUser.getTasks().get(0);
        testTask.setUser(testUser);

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(taskRepository.findById(testTask.getId())).thenReturn(Optional.of(testTask));

        try {
            TaskInfoDTO taskDTO = taskService.getTaskForUser(testUser.getId(), testTask.getId());
            Task task = taskMapper.taskInfoDtoToTask(taskDTO);

            Assertions.assertEquals(testTask.getName(), task.getName());
            Assertions.assertEquals(testTask.getDescription(), task.getDescription());
            Assertions.assertEquals(testTask.getDateTime(), task.getDateTime());
        } catch (TaskException | UserException e) {
            System.out.println("TaskException or UserException thrown: " + e.getMessage());
        }
    }

    @Test
    public void createTaskTest() {
        User testUser = testUsers.get(1);
        Task testTask = testUser.getTasks().get(1);
        TaskCreateDTO taskCreateDTO = taskMapper.taskToTaskCreateDto(testTask);
        taskCreateDTO.setDateTime(dateTimeFormatter.format(testTask.getDateTime()));

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(taskRepository.save(any(Task.class))).thenReturn(testTask);

        try {
            TaskInfoDTO taskDTO = taskService.createTask(testUser.getId(), taskCreateDTO);
            Task task = taskMapper.taskInfoDtoToTask(taskDTO);

            Assertions.assertEquals(testTask.getName(), task.getName());
            Assertions.assertEquals(testTask.getDescription(), task.getDescription());
            Assertions.assertEquals(testTask.getDateTime(), task.getDateTime());
        } catch (UserException e) {
            System.out.println("UserException thrown: " + e.getMessage());
        }
    }

    @Test
    public void updateTaskTest() {
        User testUser = testUsers.get(2);
        Task testTask = testUser.getTasks().get(0);
        testTask.setUser(testUser);

        Task modifiedTask = testTask;
        modifiedTask.setName("More painting");

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(taskRepository.findById(testTask.getId())).thenReturn(Optional.of(testTask));

        try {
            TaskInfoDTO taskDTO = taskService.updateTask(testUser.getId(), modifiedTask.getId(), taskMapper.taskToTaskUpdateDto(modifiedTask));
            Task task = taskMapper.taskInfoDtoToTask(taskDTO);

            Assertions.assertEquals(modifiedTask.getName(), task.getName());
            Assertions.assertEquals(modifiedTask.getDescription(), task.getDescription());
            Assertions.assertEquals(modifiedTask.getDateTime(), task.getDateTime());
        } catch (TaskException | UserException e) {
            System.out.println("TaskException or UserException thrown: " + e.getMessage());
        }
    }

    @Test
    public void deleteTaskTest() {
        User testUser = testUsers.get(0);
        Task testTask = testUser.getTasks().get(0);
        testTask.setUser(testUser);

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(taskRepository.findById(testTask.getId())).thenReturn(Optional.of(testTask));

        try {
            taskService.deleteTask(testUser.getId(), testTask.getId());

            verify(taskRepository).deleteById(testTask.getId());
        } catch (TaskException | UserException e) {
            System.out.println("TaskException or UserException thrown: " + e.getMessage());
        }
    }

}
