package ro.mycode.usercar.car.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.usercar.car.models.Car;
import ro.mycode.usercar.user.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car,Long> {

    @Query("SELECT DISTINCT c.marca FROM Car c")
    List<String> getAllUserNames();


    @Query("select c from Car c where c.marca= ?1 and c.pret= ?2")
    Optional<Car>findCarByMarcaAndPret(String marca, int pret);

    @Query("select c from Car c where c.marca= ?1 ")
    Optional<Car>findCarByMarca(String marca);


    @Modifying
    @Query("update Car c set c.pret= ?2 where c.marca= ?1")
    void updateMasina(String marca, int pret);



}
