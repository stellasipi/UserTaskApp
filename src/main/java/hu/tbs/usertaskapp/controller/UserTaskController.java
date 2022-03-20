package hu.tbs.usertaskapp.controller;

import hu.tbs.usertaskapp.model.dto.TaskInfoDTO;
import hu.tbs.usertaskapp.model.dto.UserCreateDTO;
import hu.tbs.usertaskapp.model.dto.UserInfoDTO;
import hu.tbs.usertaskapp.model.dto.UserUpdateDTO;
import hu.tbs.usertaskapp.service.TaskService;
import hu.tbs.usertaskapp.service.UserService;
import hu.tbs.usertaskapp.util.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
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
