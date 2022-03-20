package hu.tbs.usertaskapp.service;

import hu.tbs.usertaskapp.mapper.UserMapper;
import hu.tbs.usertaskapp.model.User;
import hu.tbs.usertaskapp.model.dto.UserCreateDTO;
import hu.tbs.usertaskapp.model.dto.UserInfoDTO;
import hu.tbs.usertaskapp.model.dto.UserUpdateDTO;
import hu.tbs.usertaskapp.repository.UserRepository;
import hu.tbs.usertaskapp.util.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public List<UserInfoDTO> getAllUsers() {
        log.info("All users are listed");
        return userRepository.findAll().stream().map(user -> userMapper.userToUserInfoDto(user)).collect(Collectors.toList());
    }

    public UserInfoDTO getUser(Integer userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            log.info("User is listed by id");
            return userMapper.userToUserInfoDto(user.get());
        } else {
            log.error("Can not find user by id");
            throw new UserException("Can not find user by id");
        }
    }

    public UserInfoDTO createUser(UserCreateDTO userCreateDTO) throws UserException {
        Optional<User> user = userRepository.findByUsername(userCreateDTO.getUsername());
        if (user.isPresent()) {
            log.error("Username is already exists");
            throw new UserException("Username is already exists");
        } else {
            User newUser = userMapper.userCreateDtoToUser(userCreateDTO);

            userRepository.save(newUser);

            log.info("User is created");
            return userMapper.userToUserInfoDto(newUser);
        }
    }

    public UserInfoDTO updateUser(Integer id, UserUpdateDTO updatedUser) throws UserException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User originalUser = userOptional.get();
            String firstName = isNewStringValid(originalUser.getFirstName(), updatedUser.getFirstName()) ? updatedUser.getFirstName() : originalUser.getFirstName();
            String lastName = isNewStringValid(originalUser.getLastName(), updatedUser.getLastName()) ? updatedUser.getLastName() : originalUser.getLastName();

            originalUser.setFirstName(firstName);
            originalUser.setLastName(lastName);

            userRepository.save(originalUser);

            log.info("User is updated");
            return userMapper.userToUserInfoDto(originalUser);
        } else {
            log.error("Can not find user by id");
            throw new UserException("Can not find user by id");
        }
    }

    private Boolean isNewStringValid(String oldString, String newString) {
        return newString != null && !newString.equals("") && !newString.equals(oldString);
    }

}
