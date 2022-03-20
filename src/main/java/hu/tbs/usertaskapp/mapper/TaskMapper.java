package hu.tbs.usertaskapp.mapper;

import hu.tbs.usertaskapp.model.Task;
import hu.tbs.usertaskapp.model.dto.TaskCreateDTO;
import hu.tbs.usertaskapp.model.dto.TaskInfoDTO;
import hu.tbs.usertaskapp.model.dto.TaskUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskUpdateDTO taskToTaskUpdateDto(Task task);

    Task taskUpdateDtoToTask(TaskUpdateDTO taskUpdateDTO);

    TaskInfoDTO taskToTaskInfoDto(Task task);

    Task taskInfoDtoToTask(TaskCreateDTO taskInfoDTO);
}
