package hu.tbs.usertaskapp.service;

import hu.tbs.usertaskapp.mapper.TaskMapper;
import hu.tbs.usertaskapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    private TaskMapper taskMapper;
}
