package hu.tbs.usertaskapp.mapper;

import hu.tbs.usertaskapp.model.User;
import hu.tbs.usertaskapp.model.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDto(User user);

    User userDtoToUser(UserDTO userDTO);
}
