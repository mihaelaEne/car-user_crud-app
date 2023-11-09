package ro.mycode.usercar.user.exceptions;

import static ro.mycode.usercar.system.Constants.NO_UPDATE;

public class NoUpdate extends RuntimeException{
    public NoUpdate(){super(NO_UPDATE);}
}
