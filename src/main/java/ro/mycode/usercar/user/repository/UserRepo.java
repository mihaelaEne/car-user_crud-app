package ro.mycode.usercar.user.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.usercar.user.models.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query("SELECT DISTINCT u.nume FROM User u")
    List<String> getAllUserNames();


    @EntityGraph(attributePaths = {"cars"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT p FROM User p")
    List<User> getAllUsers();


    @Query("select u from User u where u.nume= ?1 and u.varsta= ?2")
    Optional<User>findUserByNumeAndVarsta(String nume, int varsta);

    @Query("select u from User u where u.nume= ?1 ")
    Optional<User>findUserByNume(String nume);

    @Modifying
    @Query("update User u set u.varsta= ?2 where u.nume= ?1")
    void updateUser(String nume, int varsta);

}
