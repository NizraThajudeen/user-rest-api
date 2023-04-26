package lentra.test.userrestapi.service;

import lentra.test.userrestapi.dto.UserDto;
import lentra.test.userrestapi.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);

    UserDto filterUserByName(String name);
}
