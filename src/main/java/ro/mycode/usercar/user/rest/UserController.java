package ro.mycode.usercar.user.rest;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mycode.usercar.user.dtos.CreateUserRequest;
import ro.mycode.usercar.user.dtos.CreateUserResponse;
import ro.mycode.usercar.user.models.User;
import ro.mycode.usercar.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Slf4j
public class UserController {


    private UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users=userService.getAllUsers();
        return   new ResponseEntity<>(users,HttpStatus.OK);
    }
    @PostMapping("/createUser")
    public ResponseEntity<CreateUserResponse>addUser(@RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse = userService.addUser(createUserRequest);
        return   new ResponseEntity<>(createUserResponse,HttpStatus.CREATED);
    }



}
