package ro.mycode.usercar.user.exceptions;

import static ro.mycode.usercar.system.Constants.USERS_LIST_EMPTY;

public class UserListEmptyException extends RuntimeException{


public UserListEmptyException(){
    super(USERS_LIST_EMPTY);
}
}
