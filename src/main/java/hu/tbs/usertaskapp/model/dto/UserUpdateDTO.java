package hu.tbs.usertaskapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserUpdateDTO {

    @NotBlank
    @NotEmpty
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @NotEmpty
    @JsonProperty("last_name")
    private String lastName;
}
