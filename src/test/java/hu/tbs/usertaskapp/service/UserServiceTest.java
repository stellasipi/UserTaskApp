package hu.tbs.usertaskapp.service;

import hu.tbs.usertaskapp.mapper.UserMapper;
import hu.tbs.usertaskapp.model.User;
import hu.tbs.usertaskapp.model.dto.UserInfoDTO;
import hu.tbs.usertaskapp.repository.UserRepository;
import hu.tbs.usertaskapp.util.exception.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.Suite;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Suite
public class UserServiceTest {

    private static List<User> testUsers;
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @BeforeAll
    static void setUpTests() {
        testUsers = null;
        User testUser1 = User.builder()
                .id(1)
                .firstName("Jared")
                .lastName("Smith")
                .username("jared.smith")
                .build();
        User testUser2 = User.builder()
                .id(2)
                .firstName("Frances")
                .lastName("Woods")
                .username("frances.woods")
                .build();
        User testUser3 = User.builder()
                .id(3)
                .firstName("Danny")
                .lastName("west")
                .username("danny.west")
                .build();
        testUsers = Stream.of(testUser1, testUser2, testUser3).toList();
    }

    @Test
    public void getAllUsersTest() {

        when(userRepository.findAll()).thenReturn(testUsers);

        List<UserInfoDTO> allUsersDTO = userService.getAllUsers();

        Assertions.assertNotNull(allUsersDTO);
        List<User> allUsers = allUsersDTO.stream().map(userDTO -> userMapper.userInfoDtoToUser(userDTO)).toList();

        Assertions.assertEquals(testUsers.size(), allUsers.size());

        AtomicInteger count = new AtomicInteger();
        allUsers.forEach(user -> Assertions.assertEquals(testUsers.get(count.getAndIncrement()), user));
    }

    @Test
    public void getUserTest() {
        User testUser = testUsers.get(0);

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));

        try {
            UserInfoDTO foundUserDTO = userService.getUser(testUser.getId());
            User foundUser = userMapper.userInfoDtoToUser(foundUserDTO);
            Assertions.assertEquals(testUser, foundUser);

            Exception notFoundUserException = assertThrows(UserException.class, () -> userService.getUser(2));
            Assertions.assertTrue(notFoundUserException.getMessage().contains("Can not find user by id"));
        } catch (UserException e) {
            System.out.println("UserException thrown: " + e.getMessage());
        }
    }

    @Test
    public void createUserTest() {
        User testUser = testUsers.get(1);

        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        try {
            UserInfoDTO createdUserDTO = userService.createUser(userMapper.userToUserCreateDto(testUser));
            User createdUser = userMapper.userInfoDtoToUser(createdUserDTO);
            Assertions.assertEquals(testUser.getUsername(), createdUser.getUsername());
            Assertions.assertEquals(testUser.getLastName(), createdUser.getLastName());
            Assertions.assertEquals(testUser.getFirstName(), createdUser.getFirstName());
        } catch (UserException e) {
            System.out.println("UserException thrown: " + e.getMessage());
        }
    }

    @Test
    public void updateUserTest() {
        User testUser = testUsers.get(2);
        User modifiedTestUser = testUsers.get(2);
        modifiedTestUser.setFirstName("Daniel");

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(modifiedTestUser);

        try {
            UserInfoDTO modifiedUserDTO = userService.updateUser(modifiedTestUser.getId(), userMapper.userToUserUpdateDto(modifiedTestUser));
            User modifiedUser = userMapper.userInfoDtoToUser(modifiedUserDTO);
            Assertions.assertEquals(modifiedUser.getLastName(), modifiedUser.getLastName());
        } catch (UserException e) {
            System.out.println("UserException thrown: " + e.getMessage());
        }


    }
}
