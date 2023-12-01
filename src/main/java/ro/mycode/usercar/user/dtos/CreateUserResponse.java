package ro.mycode.usercar.user.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.mycode.usercar.user.models.User;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreateUserResponse {

    private User user;
    @Builder.Default
    private String message="Userul a fost creat cu succes";
}
