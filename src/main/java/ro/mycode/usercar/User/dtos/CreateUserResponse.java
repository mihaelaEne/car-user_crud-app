package ro.mycode.usercar.User.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.mycode.usercar.User.models.User;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreateUserResponse {

    private User user;
    @Builder.Default
    private String message="Userul a fost creat cu succes";
}
