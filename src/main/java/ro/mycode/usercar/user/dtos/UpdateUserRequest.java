package ro.mycode.usercar.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UpdateUserRequest {
    private String nume;
    private int varsta=0;
    private String username="";
    private String password="";

}
