package hu.tbs.usertaskapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * DTO for create user.
 */
@Getter
@Setter
public class UserCreateDTO {

    @NotBlank(message = "Username can't be null or whitespaces")
    @NotEmpty(message = "Username can't be null or empty")
    private String username;

    @NotBlank(message = "First name can't be null or whitespaces")
    @NotEmpty(message = "First name can't be null or empty")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last name can't be null or whitespaces")
    @NotEmpty(message = "Last name can't be null or empty")
    @JsonProperty("last_name")
    private String lastName;

}
