package hu.tbs.usertaskapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class TaskCreateDTO extends TaskUpdateDTO {

    @NotBlank
    @NotEmpty
    private String description;

    @NotBlank
    @NotEmpty
    @JsonProperty("date_time")
    private LocalDateTime dateTime;

}
