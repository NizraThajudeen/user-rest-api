package lentra.test.userrestapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lentra.test.userrestapi.entity.Course;
import lentra.test.userrestapi.entity.UserAddress;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotBlank(message = "name value has to be present")
    @Size(max = 15, message = "size violation")
    private String name;
    private int age;

    @NotBlank(message = "email has to be present")
    @Email
    private String email;

    @NotNull(message = "address value must be present")
    private UserAddress address;

    private Set<Course> courses;


}
