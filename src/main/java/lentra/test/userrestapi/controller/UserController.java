package lentra.test.userrestapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lentra.test.userrestapi.dto.UserDto;
import lentra.test.userrestapi.entity.User;
import lentra.test.userrestapi.service.UserService;
import lentra.test.userrestapi.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping("api/v1")
@Validated
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    public String getProfileValue() {
        return profileValue;
    }

    public void setProfileValue(String profileValue) {
        this.profileValue = profileValue;
    }

    @Value("${spring.application.name}")
    private String profileValue;


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = service.getAllUsers();
        LOGGER.info("info "+getProfileValue());
        LOGGER.debug("debug"+getProfileValue());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = service.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser = service.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user){
        user.setId(user.getId());
        UserDto updatedUser = service.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        service.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

    @GetMapping("users/query")
    public UserDto filterByName( @RequestParam (name="name") @NotBlank String name){
        UserDto user = service.filterUserByName(name);
        return user;
    }

}
