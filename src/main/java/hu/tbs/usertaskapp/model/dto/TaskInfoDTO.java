package hu.tbs.usertaskapp.model.dto;

import hu.tbs.usertaskapp.util.TaskStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for return a task.
 */
@Getter
@Setter
public class TaskInfoDTO extends TaskCreateDTO {

    private Integer id;

    private TaskStatus status;

}
