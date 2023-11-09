package ro.mycode.usercar.view;

import com.sun.security.auth.NTUserPrincipal;
import com.sun.tools.jconsole.JConsoleContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ro.mycode.usercar.user.dtos.CreateUserRequest;
import ro.mycode.usercar.user.dtos.UpdateUserRequest;
import ro.mycode.usercar.user.exceptions.NoUpdate;
import ro.mycode.usercar.user.exceptions.UserDoesntExistException;
import ro.mycode.usercar.user.exceptions.UserExistException;
import ro.mycode.usercar.user.exceptions.UserListEmptyException;
import ro.mycode.usercar.user.models.User;
import ro.mycode.usercar.user.service.UserService;

import java.util.List;
import java.util.Optional;
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
        System.out.println("2-add user");
        System.out.println("3-delete user ");
        System.out.println("4-update user");
    }

    public void play() {
        int alegere = 0;
        boolean running = true;

        while (running) {
            meniu();
            alegere = Integer.parseInt(scanner.nextLine());

            switch (alegere) {
                case 1:
                    afisareUseri();
                    break;

                case 2:
                    addUser();
                    break;

                case 3:
                    deleteUser();
                    break;
                case 4:
                    updateUser();
                    break;

                default:
                    System.out.println("Alegerea este gresita!");
            }
        }
    }


    private void afisareUseri() {
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


    private void deleteUser() {
        try {
            System.out.println("Introduceti numele si varsta utilizatorului: ");
            System.out.println("Nume:");
            String nume = scanner.nextLine();
            System.out.println("Varsta: ");
            int varsta = Integer.parseInt(scanner.nextLine());

            CreateUserRequest createUserRequest = CreateUserRequest.builder()
                    .nume(nume)
                    .varsta(varsta)
                    .build();

            userService.deleteUser(createUserRequest);

        } catch (UserDoesntExistException userDoesntExistException) {
            System.out.println(userDoesntExistException.getMessage());
        }
    }


    private void updateUser() {

        try {
            System.out.println("introduceti numele: ");
            String nume = scanner.nextLine();
            System.out.println(" introduceti varsta moua: ");
            int varsta = Integer.parseInt(scanner.nextLine());
            System.out.println("introduceti noul username: ");
            String username = scanner.nextLine();
            System.out.println("introduceti new password: ");
            String password = scanner.nextLine();

            UpdateUserRequest updateUserRequest1 = UpdateUserRequest.builder()
                    .nume(nume)
                    .varsta(varsta)
                    .username(username)
                    .password(password)
                    .build();
            userService.updateUser(updateUserRequest1);

        } catch (NoUpdate noUpdate) {
            System.out.println(noUpdate.getMessage());
        }

    }

}
