package hu.tbs.usertaskapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * DTO for update task.
 */
@Getter
@Setter
public class TaskUpdateDTO {

    @NotBlank(message = "Name can't be null or whitespaces")
    @NotEmpty(message = "Name can't be null or empty")
    private String name;

}
