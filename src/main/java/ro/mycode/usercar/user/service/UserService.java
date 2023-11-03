package ro.mycode.usercar.user.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.mycode.usercar.car.models.Car;
import ro.mycode.usercar.car.repository.CarRepo;
import ro.mycode.usercar.user.dtos.CreateCarRequest;
import ro.mycode.usercar.user.dtos.CreateUserRequest;
import ro.mycode.usercar.user.exceptions.UserExistException;
import ro.mycode.usercar.user.exceptions.UserListEmptyException;
import ro.mycode.usercar.user.models.User;
import ro.mycode.usercar.user.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;



    public UserService (UserRepo userRepo){
        this.userRepo=userRepo;
    }


    public List<User> getAllUsers()  {
        List<User> all = userRepo.findAll();
        if(all.size()==0){
            throw new UserListEmptyException();
        }
        return all;
    }


    @Transactional
    public void addUser(CreateUserRequest createUserRequest){

        //verificam daca userul exista deja in baza de date
        Optional<User> userByNume = userRepo.findUserByNume(createUserRequest.getNume());
        if(userByNume.isPresent()){

            throw  new UserExistException();
        }
         User user=User.builder().nume(createUserRequest.getNume())
                 .varsta(createUserRequest.getVarsta())
                         .build();
         userRepo.saveAndFlush(user);
    }

    @Transactional
    public void addCar(CreateCarRequest createCarRequest){

    }

}
