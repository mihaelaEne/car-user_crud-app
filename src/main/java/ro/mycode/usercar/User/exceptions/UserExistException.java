package ro.mycode.usercar.User.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static ro.mycode.usercar.User.system.Constants.USER_EXIST;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserExistException extends RuntimeException{

    public UserExistException(){super(USER_EXIST);}
}
