package hu.tbs.usertaskapp.service;

import hu.tbs.usertaskapp.mapper.UserMapper;
import hu.tbs.usertaskapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

}
