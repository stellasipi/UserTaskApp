package hu.tbs.usertaskapp.mapper;

import hu.tbs.usertaskapp.model.User;
import hu.tbs.usertaskapp.model.dto.UserCreateDTO;
import hu.tbs.usertaskapp.model.dto.UserInfoDTO;
import hu.tbs.usertaskapp.model.dto.UserUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserUpdateDTO userToUserUpdateDto(User user);

    User userUpdateDtoToUser(UserUpdateDTO userUpdateDTO);

    UserCreateDTO userToUserCreateDto(User user);

    User userCreateDtoToUser(UserCreateDTO userCreateDTO);

    UserInfoDTO userToUserInfoDto(User user);

    User userInfoDtoToUser(UserInfoDTO userInfoDTO);
}
