package lentra.test.userrestapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotBlank
    @Size(min = 5, max = 15)
    private String name;
    private int age;

    @NotBlank
    @Email
    private String email;


}
