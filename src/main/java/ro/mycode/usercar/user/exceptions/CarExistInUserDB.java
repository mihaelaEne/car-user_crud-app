package ro.mycode.usercar.user.exceptions;

import static ro.mycode.usercar.system.Constants.CAR_EXIST_IN_DB;

public class CarExistInUserDB extends RuntimeException{

    public CarExistInUserDB(){super(CAR_EXIST_IN_DB);}
}
