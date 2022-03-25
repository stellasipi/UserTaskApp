package hu.tbs.usertaskapp.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for return a user.
 */
@Getter
@Setter
public class UserInfoDTO extends UserCreateDTO {

    private Integer id;

}
