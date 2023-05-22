package lentra.test.userrestapi.service;

import lentra.test.userrestapi.dto.UserDto;
import lentra.test.userrestapi.entity.User;
import lentra.test.userrestapi.exception.UserNotFoundException;
//import lentra.test.userrestapi.mapper.UserMapper;
import lentra.test.userrestapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<UserDto> getAllUsers(){
        List<User> users = repository.findAll();
        List<UserDto> userDtos = new ArrayList<UserDto>();
        LOGGER.info("info ");
        LOGGER.debug("debug");

        for (User user: users) {
            userDtos.add(modalMapper.map(user, UserDto.class));
        }
        return userDtos;

    }

    @Override
    public UserDto getUserById(Long userId){
        Optional<User> optionalUser = Optional.ofNullable(repository.findById(userId).orElseThrow(() -> new UserNotFoundException("user with given id does not exist")));
        User user = optionalUser.get();

        UserDto userDto = modalMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto createUser(UserDto userDto){
        // Convert UserDto into User JPA Entity

        User user = modalMapper.map(userDto, User.class);
        User savedUser = repository.save(user);
        // Convert User JPA entity to UserDto
        UserDto savedUserDto = modalMapper.map(savedUser, UserDto.class);
        LOGGER.info(savedUserDto.getName()+ " has been created successfully");

        return savedUserDto;
    }

    @Override
    public UserDto updateUser(UserDto user){
        Optional<User> optionalUserEntity = repository.findById(user.getId());
        if(optionalUserEntity.isPresent()) {
            User userEntity = optionalUserEntity.get();
            userEntity.setName(user.getName());
            userEntity.setAge(user.getAge());
            userEntity.setEmail(user.getEmail());
            User updatedUser = repository.save(userEntity);
            UserDto updatedUserDto = modalMapper.map(updatedUser, UserDto.class);
            LOGGER.info(updatedUserDto.getName()+ " has been updated successfully");
            return updatedUserDto;

        }
        else{
            LOGGER.error("Failed to update user "+user.getName());
            throw new UserNotFoundException("cannot find a resource with id "+user.getId()+" for update operation");
        }



    }

    @Override
    public void deleteUser(Long userId){
//        repository.deleteById(userId);
        Optional<User> user = repository.findById(userId);
        if(user.isPresent()) {
            repository.deleteById(userId);
            LOGGER.info("User with given id has been deleted successfully");
        } else{
            LOGGER.error("cannot find a user with id "+userId);
            throw new UserNotFoundException("cannot find a user with id "+userId);
        }
    }

    @Override
    public UserDto filterUserByName(String name){
        Optional<User> optionalUser = Optional.ofNullable(repository.findByName(name));
        User user = optionalUser.get();

        UserDto userDto = modalMapper.map(user, UserDto.class);
        return userDto;
    }
}
