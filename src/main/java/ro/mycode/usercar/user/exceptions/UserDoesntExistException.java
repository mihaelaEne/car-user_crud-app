package ro.mycode.usercar.user.exceptions;

import static ro.mycode.usercar.system.Constants.USER_DOESNT_EXIST;

public class UserDoesntExistException extends RuntimeException{

    public UserDoesntExistException(){super(USER_DOESNT_EXIST);}
}
