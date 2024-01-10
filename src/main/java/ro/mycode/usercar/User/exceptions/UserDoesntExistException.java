package ro.mycode.usercar.User.exceptions;

import static ro.mycode.usercar.User.system.Constants.USER_DOESNT_EXIST;

public class UserDoesntExistException extends RuntimeException{

    public UserDoesntExistException(){super(USER_DOESNT_EXIST);}
}
