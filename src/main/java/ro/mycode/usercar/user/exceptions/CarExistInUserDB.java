package ro.mycode.usercar.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static ro.mycode.usercar.system.Constants.CAR_EXIST_IN_DB;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CarExistInUserDB extends RuntimeException{

    public CarExistInUserDB(){super(CAR_EXIST_IN_DB);}
}
