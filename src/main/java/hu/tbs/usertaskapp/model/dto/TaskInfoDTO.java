package hu.tbs.usertaskapp.model.dto;

import hu.tbs.usertaskapp.util.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInfoDTO extends TaskCreateDTO {

    private Integer id;

    private TaskStatus status;

}
