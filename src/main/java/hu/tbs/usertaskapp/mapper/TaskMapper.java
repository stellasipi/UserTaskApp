package hu.tbs.usertaskapp.mapper;

import hu.tbs.usertaskapp.model.Task;
import hu.tbs.usertaskapp.model.dto.TaskDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO taskToTaskDto(Task task);

    Task taskDtoToTask(TaskDTO taskDTO);
}
