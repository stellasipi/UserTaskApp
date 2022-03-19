package hu.tbs.usertaskapp.controller;

import hu.tbs.usertaskapp.model.dto.TaskInfoDTO;
import hu.tbs.usertaskapp.model.dto.UserInfoDTO;
import hu.tbs.usertaskapp.service.TaskService;
import hu.tbs.usertaskapp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    private TaskService taskService;

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
        return null;
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserInfoDTO> getUser() {
        return null;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserInfoDTO> createUser() {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserInfoDTO> updateUser() {
        return null;
    }

    @GetMapping(value = "/{user_id}/task", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<TaskInfoDTO>> getAllTasksForUser() {
        return null;
    }

    @GetMapping(value = "/{user_id}/task/{task_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskInfoDTO> getTaskForUser() {
        return null;
    }

    @PostMapping(value = "/{user_id}/task", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskInfoDTO> createTask() {
        return null;
    }

    @PutMapping(value = "/{user_id}/task/{task_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskInfoDTO> updateTask() {
        return null;
    }

    @DeleteMapping(value = "/{user_id}/task/{task_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskInfoDTO> deleteTask() {
        return null;
    }

}
