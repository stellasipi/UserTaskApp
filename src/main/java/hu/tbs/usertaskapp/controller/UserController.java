package hu.tbs.usertaskapp.controller;

import hu.tbs.usertaskapp.model.dto.TaskDTO;
import hu.tbs.usertaskapp.model.dto.UserDTO;
import hu.tbs.usertaskapp.service.TaskService;
import hu.tbs.usertaskapp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    private TaskService taskService;

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> getAllUsers() {
        return null;
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> getUser() {
        return null;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> createUser() {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> updateUser() {
        return null;
    }

    @GetMapping(value = "/{user_id}/task", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskDTO> getAllTasksForUser() {
        return null;
    }

    @GetMapping(value = "/{user_id}/task/{task_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskDTO> getTaskForUser() {
        return null;
    }

    @PostMapping(value = "/{user_id}/task", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskDTO> createTask() {
        return null;
    }

    @PutMapping(value = "/{user_id}/task/{task_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskDTO> updateTask() {
        return null;
    }

    @DeleteMapping(value = "/{user_id}/task/{task_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskDTO> deleteTask() {
        return null;
    }

}
