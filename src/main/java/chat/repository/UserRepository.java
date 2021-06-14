package chat.repository;


import chat.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT u.id, u.name, u.surname, u.age, u.password FROM users as u", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "SELECT u.id, u.name, u.surname, u.age, u.password FROM users as u WHERE u.id = :id", nativeQuery = true)
    User getUser(@Param("id") int id);

    @Modifying
    @Query(value = "INSERT INTO users as u (name, surname, age, password) VALUES(:name, :surname, :age, :password)", nativeQuery = true)
    @Transactional
    void insertUser(@Param("name") String name, @Param("surname") String surname, @Param("age") int age, @Param("password") String password);

    @Modifying
    @Query(value = "UPDATE users as u SET name = :name, surname = :surname, age = :age, password = :password WHERE u.id = :id", nativeQuery = true)
    @Transactional
    void updateUser(@Param("name") String name, @Param("surname") String surname, @Param("age") int age, @Param("password") String password, @Param("id") int id);

    @Modifying
    @Query(value = "DELETE FROM users as u WHERE u.id = :id", nativeQuery = true)
    @Transactional
    void deleteUser(@Param("id") int id);
}
