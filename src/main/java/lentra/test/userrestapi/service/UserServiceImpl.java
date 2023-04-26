package lentra.test.userrestapi.service;

import lentra.test.userrestapi.dto.UserDto;
import lentra.test.userrestapi.entity.User;
import lentra.test.userrestapi.exception.ResourceNotFoundException;
//import lentra.test.userrestapi.mapper.UserMapper;
import lentra.test.userrestapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modalMapper;

    @Override
    public List<UserDto> getAllUsers(){
        List<User> users = repository.findAll();
        List<UserDto> userDtos = new ArrayList<UserDto>();

        for (User user: users) {
            userDtos.add(modalMapper.map(user, UserDto.class));
        }
        return userDtos;

//        return users.stream().map(user ->modalMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());

    }

    @Override
    public UserDto getUserById(Long userId){
        Optional<User> optionalUser = Optional.ofNullable(repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id does not exist")));
        User user = optionalUser.get();

        UserDto userDto = modalMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto createUser(UserDto userDto){
        // Convert UserDto into User JPA Entity
        System.out.println("dto name"+userDto.getName());
        User user = modalMapper.map(userDto, User.class);
        User savedUser = repository.save(user);
        // Convert User JPA entity to UserDto
        UserDto savedUserDto = modalMapper.map(savedUser, UserDto.class);

        return savedUserDto;
    }

    @Override
    public UserDto updateUser(UserDto user){
        User existingUser = repository.findById(user.getId()).get();
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        User updatedUser = repository.save(existingUser);
        UserDto updatedUserDto = modalMapper.map(updatedUser, UserDto.class);
        return updatedUserDto;

    }

    @Override
    public void deleteUser(Long userId){
        repository.deleteById(userId);
    }

    @Override
    public UserDto filterUserByName(String name){
        Optional<User> optionalUser = Optional.ofNullable(repository.findByName(name));
        User user = optionalUser.get();

        UserDto userDto = modalMapper.map(user, UserDto.class);
        return userDto;
    }
}
