package ro.mycode.usercar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.mycode.usercar.car.repository.CarRepo;
import ro.mycode.usercar.user.repository.UserRepo;
import ro.mycode.usercar.view.View;

@SpringBootApplication
public class UserCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCarApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepo userRepo, CarRepo carRepo, View view){
        return args -> {

        view.play();

//            userRepo.getAllUsers().stream().forEach(user -> {
//
//                System.out.println(user);
//                System.out.println(user.getCars());
//            });




        };
    }

}
