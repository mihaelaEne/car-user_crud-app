package ro.mycode.usercar.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ro.mycode.usercar.car.models.Car;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class User implements Comparable<User> {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String nume;
    private int varsta;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();




    public void addCar(Car car){

        this.cars.add(car);

        car.setUser(this);

    }

    public void deleteCar(Car car){
        if(this.cars.contains(car)){
            this.cars.remove(car);
            car.setUser(null);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", varsta=" + varsta +
                ", username='" + username + '\'' +
                ", password='" + password +
                '}';
    }

    @Override
    public int compareTo(User o) {
        return 0;
    }
}

