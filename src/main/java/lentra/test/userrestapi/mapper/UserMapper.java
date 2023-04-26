//package lentra.test.userrestapi.mapper;
//
//import lentra.test.userrestapi.dto.UserDto;
//import lentra.test.userrestapi.entity.User;
//
//public class UserMapper {
//
//    // Convert User JPA Entity into UserDto
//    public static UserDto mapToUserDto(User user){
//        UserDto userDto = new UserDto(
//                user.getId(),
//                user.getName(),
//                user.getAge(),
//                user.getEmail()
//        );
//        return userDto;
//    }
//
//    // Convert UserDto into User JPA Entity
//    public static User mapToUser(UserDto userDto){
//        User user = new User(
//                userDto.getId(),
//                userDto.getName(),
//                userDto.getAge(),
//                userDto.getEmail()
//        );
//        return user;
//    }
//}
