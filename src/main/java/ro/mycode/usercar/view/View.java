package ro.mycode.usercar.view;

import com.sun.security.auth.NTUserPrincipal;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.stereotype.Component;
import ro.mycode.usercar.user.dtos.CreateUserRequest;
import ro.mycode.usercar.user.exceptions.UserExistException;
import ro.mycode.usercar.user.exceptions.UserListEmptyException;
import ro.mycode.usercar.user.models.User;
import ro.mycode.usercar.user.service.UserService;

import java.util.List;
import java.util.Scanner;


@Component
public class View {

    private Scanner scanner;


    private UserService userService;

    private User user;


    public View(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }


    public void meniu() {
        System.out.println("Alege varianta dorita");
        System.out.println("1-toti users");
        System.out.println("2-toate masinile");
        System.out.println("3-adauga un user");
        System.out.println("4-addCar");
        System.out.println("5-sterge un user");
        System.out.println("6-delete car");
        System.out.println("7-modifica un user ");
        System.out.println("8-modifica o masina ");
    }

    public void play() {
        int alegere = 0;
        boolean running = true;

        while (running) {
            meniu();
            alegere = Integer.parseInt(scanner.nextLine());

            switch (alegere) {
                case 1:
                    afisare();
                    break;

                case 2:
                    addUser();
                    break;

                default:
                    System.out.println("Alegerea este gresita!");
            }
        }
    }


    private void afisare() {
        try {
            List<User> users = userService.getAllUsers();
            users.forEach(user -> {
                System.out.println(user);
            });
        } catch (UserListEmptyException userListEmptyException) {
            System.out.println(userListEmptyException.getMessage());
        }
    }

    private void addUser() {


        try {
            System.out.println("Introduceti numele si varsta: ");
            System.out.println("Nume: ");
            String nume = scanner.nextLine();
            System.out.println("Varsta: ");
            int varsta = Integer.parseInt(scanner.nextLine());


            CreateUserRequest createUserRequest = CreateUserRequest.builder()
                    .nume(nume)
                    .varsta(varsta)
                    .build();

            userService.addUser(createUserRequest);

        } catch (UserExistException userExistException) {
            System.out.println(userExistException.getMessage());
        }

    }

}
