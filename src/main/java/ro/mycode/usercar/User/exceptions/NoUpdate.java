package ro.mycode.usercar.User.exceptions;

import static ro.mycode.usercar.User.system.Constants.NO_UPDATE;

public class NoUpdate extends RuntimeException{
    public NoUpdate(){super(NO_UPDATE);}
}
