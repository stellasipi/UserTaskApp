package hu.tbs.usertaskapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TaskUpdateDTO {

    @NotBlank
    @NotEmpty
    private String name;

}
