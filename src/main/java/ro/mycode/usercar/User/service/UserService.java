package ro.mycode.usercar.User.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.mycode.usercar.User.dtos.CreateUserRequest;
import ro.mycode.usercar.User.dtos.CreateUserResponse;
import ro.mycode.usercar.User.dtos.UpdateUserRequest;
import ro.mycode.usercar.User.exceptions.NoUpdate;
import ro.mycode.usercar.User.exceptions.UserDoesntExistException;
import ro.mycode.usercar.User.exceptions.UserExistException;
import ro.mycode.usercar.User.exceptions.UserListEmptyException;
import ro.mycode.usercar.User.models.User;
import ro.mycode.usercar.User.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public List<User> getAllUsers() {
        List<User> all = userRepo.findAll();
        if (all.size() == 0) {
            throw new UserListEmptyException();
        }
        return all;
    }


    @Transactional
    public CreateUserResponse addUser(CreateUserRequest createUserRequest) {

        //verificam daca userul exista deja in baza de date
        Optional<User> userByNume = userRepo.findUserByNume(createUserRequest.getNume());
        if (userByNume.isPresent()) {

            throw new UserExistException();
        }
        User user = User.builder()
                .nume(createUserRequest.getNume())
                .varsta(createUserRequest.getVarsta())
                .username(createUserRequest.getUsername())
                .password(createUserRequest.getPassword())
                .build();
      User user2=  userRepo.saveAndFlush(user);

      return  CreateUserResponse.builder().user(user2).build();
    }

    @Transactional
    public void deleteUser(CreateUserRequest createUserRequest) {
        Optional<User> user = userRepo.findUserByNumeAndVarsta(createUserRequest.getNume(), createUserRequest.getVarsta());
        if (user.isPresent()) {
            userRepo.delete(user.get());
        } else {
            throw new UserDoesntExistException();
        }
    }

    @Transactional
    public void updateUser(UpdateUserRequest updateUserRequest) {
        Optional<User>userOpt=userRepo.findUserByNume(updateUserRequest.getNume());
        if(userOpt.isPresent()){

            User user = userOpt.get();
            if(updateUserRequest.getVarsta()!=0) {
                user.setVarsta(updateUserRequest.getVarsta());
            }

            if(!updateUserRequest.getUsername().equals("")){
                user.setUsername(updateUserRequest.getUsername());
            }

            if(!updateUserRequest.getPassword().equals("")){
                user.setPassword(updateUserRequest.getPassword());
            }

            userRepo.saveAndFlush(user);

        }else{
            throw new NoUpdate();
        }
    }

}
