package ro.mycode.usercar.car.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ro.mycode.usercar.user.models.User;

@Entity
@Table(name="cars")
@Data
@AllArgsConstructor
@SuperBuilder

@NoArgsConstructor
public class Car implements Comparable<Car>{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String marca;
    private int pret;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", pret=" + pret +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Override
    public int compareTo(Car o) {
        return 0;
    }
}
