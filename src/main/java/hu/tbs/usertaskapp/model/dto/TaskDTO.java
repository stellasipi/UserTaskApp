package hu.tbs.usertaskapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDTO {

    private Integer id;

    private String name;

    private String description;

    @JsonProperty("date_time")
    private LocalDateTime dateTime;
}
