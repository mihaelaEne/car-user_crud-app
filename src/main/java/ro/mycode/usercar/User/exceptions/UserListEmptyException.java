package ro.mycode.usercar.User.exceptions;

import static ro.mycode.usercar.User.system.Constants.USERS_LIST_EMPTY;

public class UserListEmptyException extends RuntimeException{


public UserListEmptyException(){
    super(USERS_LIST_EMPTY);
}
}
