package hu.tbs.usertaskapp.service;

import hu.tbs.usertaskapp.mapper.TaskMapper;
import hu.tbs.usertaskapp.model.Task;
import hu.tbs.usertaskapp.model.User;
import hu.tbs.usertaskapp.model.dto.TaskCreateDTO;
import hu.tbs.usertaskapp.model.dto.TaskInfoDTO;
import hu.tbs.usertaskapp.model.dto.TaskUpdateDTO;
import hu.tbs.usertaskapp.repository.TaskRepository;
import hu.tbs.usertaskapp.repository.UserRepository;
import hu.tbs.usertaskapp.util.TaskStatus;
import hu.tbs.usertaskapp.util.exception.TaskException;
import hu.tbs.usertaskapp.util.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class TaskService {

    private UserRepository userRepository;

    private TaskRepository taskRepository;

    private TaskMapper taskMapper;

    public List<TaskInfoDTO> getAllTasksForUser(Integer userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Task> tasksForUser = taskRepository.findByUser(user.get());
            log.info("Tasks for user is listed");
            return tasksForUser.stream().map(task -> taskMapper.taskToTaskInfoDto(task)).collect(Collectors.toList());
        } else {
            log.error("User id does not exists");
            throw new UserException("User id does not exists");
        }
    }

    public TaskInfoDTO getTaskForUser(Integer userId, Integer taskId) throws TaskException, UserException {
        Optional<User> user = userRepository.findById(userId);
        Optional<Task> task = taskRepository.findById(taskId);
        Task taskFound = new Task();
        try {
            if (isUserAndTaskExists(user, task)) {
                log.info("Task for user is listed");
                taskFound = task.get();
            }
            return taskMapper.taskToTaskInfoDto(taskFound);
        } catch (UserException | TaskException e) {
            throw e;
        }
    }

    public TaskInfoDTO createTask(Integer userId, TaskCreateDTO taskCreateDTO) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            log.error("User id does not exists");
            throw new UserException("User id does not exists");
        } else {
            Task task = Task.builder()
                    .user(user.get())
                    .name(taskCreateDTO.getName())
                    .description(taskCreateDTO.getDescription())
                    .dateTime(LocalDateTime.parse(taskCreateDTO.getDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .status(TaskStatus.PENDING)
                    .build();
            taskRepository.save(task);
            log.info("Task is created");
            return taskMapper.taskToTaskInfoDto(task);
        }
    }

    public TaskInfoDTO updateTask(Integer userId, Integer taskId, TaskUpdateDTO taskUpdateDTO) throws TaskException, UserException {
        Optional<User> user = userRepository.findById(userId);
        Optional<Task> originalTask = taskRepository.findById(taskId);
        Task task = new Task();
        try {
            if (isUserAndTaskExists(user, originalTask)) {
                task = originalTask.get();

                task.setName(taskUpdateDTO.getName());
                taskRepository.save(task);
                log.info("Task is updated");
            }
            return taskMapper.taskToTaskInfoDto(task);
        } catch (UserException | TaskException e) {
            throw e;
        }
    }

    public void deleteTask(Integer userId, Integer taskId) throws TaskException, UserException {
        Optional<User> user = userRepository.findById(userId);
        Optional<Task> task = taskRepository.findById(taskId);
        try {
            if (isUserAndTaskExists(user, task)) {
                taskRepository.deleteById(taskId);
                log.info("Task is deleted");
            }
        } catch (UserException | TaskException e) {
            throw e;
        }
    }

    public Boolean isUserAndTaskExists(Optional<User> user, Optional<Task> task) throws TaskException, UserException {
        if (!user.isPresent()) {
            log.error("User id does not exists");
            throw new UserException("User id does not exists");
        } else if (!task.isPresent() || !task.get().getUser().getId().equals(user.get().getId())) {
            log.error("Task id does not exists or task id's user id does not match to the requested user id");
            throw new TaskException("Task id does not exists or task id's user id does not match to the requested user id");
        } else {
            return true;
        }
    }

    @Scheduled(fixedDelay = 300000)
    public void checkIfTasksHadExpired() {
        List<Task> expiredTasks = taskRepository.findByDateTimeBeforeAndStatus(LocalDateTime.now(), TaskStatus.PENDING);
        if (!expiredTasks.isEmpty()) {
            expiredTasks.stream().forEach(task -> {
                task.setStatus(TaskStatus.DONE);
                taskRepository.save(task);
                log.info("Task id: " + task.getId() + " with task name: " + task.getName() + " updated to DONE");
            });
        }
    }
}
