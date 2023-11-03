package ro.mycode.usercar.user.exceptions;

import static ro.mycode.usercar.system.Constants.USER_EXIST;

public class UserExistException extends RuntimeException{

    public UserExistException(){super(USER_EXIST);}
}
