package hu.tbs.usertaskapp.controller;

import hu.tbs.usertaskapp.model.dto.*;
import hu.tbs.usertaskapp.service.TaskService;
import hu.tbs.usertaskapp.service.UserService;
import hu.tbs.usertaskapp.util.exception.TaskException;
import hu.tbs.usertaskapp.util.exception.UserException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserTaskController {

    private UserService userService;

    private TaskService taskService;

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserInfoDTO> getUser(@PathVariable Integer id) {
        try {
            UserInfoDTO user = userService.getUser(id);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserInfoDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            UserInfoDTO user = userService.createUser(userCreateDTO);
            UriComponents uriComponents = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId());
            return ResponseEntity.created(uriComponents.toUri()).body(user);
        } catch (UserException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserInfoDTO> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            UserInfoDTO user = userService.updateUser(id, userUpdateDTO);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{user_id}/task", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<TaskInfoDTO>> getAllTasksForUser(@PathVariable(name = "user_id") Integer userId) {
        try {
            List<TaskInfoDTO> tasks = taskService.getAllTasksForUser(userId);
            return ResponseEntity.ok(tasks);
        } catch (UserException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{user_id}/task/{task_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskInfoDTO> getTaskForUser(@PathVariable(name = "user_id") Integer userId, @PathVariable(name = "task_id") Integer taskId) {
        try {
            TaskInfoDTO task = taskService.getTaskForUser(userId, taskId);
            return ResponseEntity.ok(task);
        } catch (TaskException | UserException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/{user_id}/task", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskInfoDTO> createTask(@PathVariable(name = "user_id") Integer userId, @Valid @RequestBody TaskCreateDTO taskCreateDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            TaskInfoDTO task = taskService.createTask(userId, taskCreateDTO);
            UriComponents uriComponents = uriComponentsBuilder.path("/user/{user_id}/task/{task_id}").buildAndExpand(userId, task.getId());
            return ResponseEntity.created(uriComponents.toUri()).body(task);
        } catch (UserException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{user_id}/task/{task_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskInfoDTO> updateTask(@PathVariable(name = "user_id") Integer userId, @PathVariable(name = "task_id") Integer taskId, @Valid @RequestBody TaskUpdateDTO taskUpdateDTO) {
        try {
            TaskInfoDTO task = taskService.updateTask(userId, taskId, taskUpdateDTO);
            return ResponseEntity.ok(task);
        } catch (TaskException | UserException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{user_id}/task/{task_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity deleteTask(@PathVariable(name = "user_id") Integer userId, @PathVariable(name = "task_id") Integer taskId) {
        try {
            taskService.deleteTask(userId, taskId);
            return ResponseEntity.ok().build();
        } catch (TaskException | UserException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
