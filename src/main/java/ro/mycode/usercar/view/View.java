package ro.mycode.usercar.view;

import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ro.mycode.usercar.car.models.Car;
import ro.mycode.usercar.car.repository.CarRepo;
import ro.mycode.usercar.user.models.User;
import ro.mycode.usercar.user.repository.UserRepo;

import java.util.Optional;
import java.util.Scanner;


@Component
public class View {

    private Scanner scanner;
    private UserRepo userRepo;

    private CarRepo carRepo;

    private User user;


    public View(UserRepo userRepo, CarRepo carRepo) {
        this.userRepo = userRepo;
        this.carRepo = carRepo;
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


    }

    public void play() {
        int alegere = 0;
        boolean running = true;

        while (running) {
            meniu();
            alegere = Integer.parseInt(scanner.nextLine());

            switch (alegere) {
                case 1:
                    afisareUser();
                    break;
                case 2:
                    afisareCar();
                    break;

                case 3:
                    adaugaUser();
                    break;
                case 4:
                    addCar();
                    break;

                case 5:
                    stergereUser();
                    break;

                case 6:
                    deleteCar();
                    break;
                case 7:
                    updateUser();
                    break;


                default:
                    System.out.println("Alegerea este gresita!");
            }
        }
    }

    public void afisareUser() {
        this.userRepo.findAll().forEach(user -> {
            System.out.println(user);
        });
    }

    public void afisareCar() {
        this.carRepo.findAll().forEach(car -> {
            System.out.println(car);
        });
    }

    @Transactional
    public void adaugaUser() {
        System.out.println("Nume:");
        String nume = scanner.nextLine();
        System.out.println("Varsta:");
        int varsta = Integer.parseInt(scanner.nextLine());

        User user = User.builder()
                .nume(nume)
                .varsta(varsta)
                .build();

        userRepo.saveAndFlush(user);
        System.out.println("Userul a fost adaugat cu succes");
    }

    @Transactional
    public void stergereUser() {
        System.out.println("Introduceti numele si varsta");
        System.out.println("Nume: ");
        String nume = scanner.nextLine();
        System.out.println("Varsta: ");
        int varsta = Integer.parseInt(scanner.nextLine());

        Optional<User> user = userRepo.findUserByNumeAndVarsta(nume, varsta);
        if (user.isPresent()) {
            userRepo.delete(user.get());
            System.out.println("Userul a fost sters din baza de date");
        } else {
            System.out.println("Userul nu exista ");
        }

    }


    @Transactional
    public void updateUser() {
        System.out.println("Introduceti numele ");
        System.out.println("Nume: ");
        String nume = scanner.nextLine();


        Optional<User> user = userRepo.findUserByNume(nume);
        if (user.isPresent()) {
            System.out.println("varsta updata: ");
            int varsta = Integer.parseInt(scanner.nextLine());

            User user1 = user.get();
            user1.setVarsta(varsta);

            userRepo.saveAndFlush(user1);

            System.out.println("Varsta a fost actualizata");

        } else {
            System.out.println("Userul nu exista ");
        }

    }


    @Transactional
    public void addCar() {
        System.out.println("Introduceti numele ");
        System.out.println("Nume: ");
        String nume = scanner.nextLine();

        Optional<User> searchedUser = userRepo.findUserByNume(nume);
        if (searchedUser.isPresent()) {
            this.user = searchedUser.get();
            System.out.println("Introduceti marca: ");
            String marca = scanner.nextLine();
            System.out.println("Introduceti pretul: ");
            int pret = Integer.parseInt(scanner.nextLine());


            Car car1 = Car.builder()
                    .marca(marca)
                    .pret(pret)
                    .build();


            this.user.addCar(car1);


            this.userRepo.saveAndFlush(user);

        } else {
            System.out.println("Userul nu exista ");
        }


    }

    @Transactional
    public void deleteCar() {
        System.out.println("Introduceti numele ");
        System.out.println("Nume: ");
        String nume = scanner.nextLine();

        Optional<User> searchedUser = userRepo.findUserByNume(nume);
        if (searchedUser.isPresent()) {
            this.user = searchedUser.get();
            System.out.println("Introduceti marca: ");
            String marca = scanner.nextLine();

            Optional<Car> serachedCar = carRepo.findCarByMarca(marca);
            if (serachedCar.isPresent()) {
                carRepo.delete(serachedCar.get());
                System.out.println("Masina a fost stearsa de pe lista userului");
            } else {
                System.out.println("Masina nu exista");
            }

        } else {
            System.out.println("Userul nu exista ");
        }


    }


}
