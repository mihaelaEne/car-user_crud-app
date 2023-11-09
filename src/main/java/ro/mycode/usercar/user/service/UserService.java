package ro.mycode.usercar.user.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.mycode.usercar.car.models.Car;
import ro.mycode.usercar.car.repository.CarRepo;
import ro.mycode.usercar.user.dtos.CreateCarRequest;
import ro.mycode.usercar.user.dtos.CreateUserRequest;
import ro.mycode.usercar.user.dtos.UpdateUserRequest;
import ro.mycode.usercar.user.exceptions.NoUpdate;
import ro.mycode.usercar.user.exceptions.UserDoesntExistException;
import ro.mycode.usercar.user.exceptions.UserExistException;
import ro.mycode.usercar.user.exceptions.UserListEmptyException;
import ro.mycode.usercar.user.models.User;
import ro.mycode.usercar.user.repository.UserRepo;

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
    public void addUser(CreateUserRequest createUserRequest) {

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
        userRepo.saveAndFlush(user);
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
