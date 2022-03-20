package hu.tbs.usertaskapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TaskCreateDTO extends TaskUpdateDTO {

    @NotBlank(message = "Description can't be null or whitespaces")
    @NotEmpty(message = "Description can't be null or empty")
    private String description;

    @NotBlank(message = "Date time can't be null or whitespaces")
    @NotEmpty(message = "Date time can't be null or empty")
    @JsonProperty("date_time")
    private String dateTime;

}
