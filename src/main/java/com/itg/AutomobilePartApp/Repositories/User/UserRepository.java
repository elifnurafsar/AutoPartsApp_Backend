package com.itg.AutomobilePartApp.Repositories.User;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itg.AutomobilePartApp.DTO.User.UserDTO;
import com.itg.AutomobilePartApp.Entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "BEGIN TRANSACTION;  \n" +
            "       INSERT INTO users (username, password, enabled, auth) VALUES (:#{#user.username}, :#{#user.password}, :#{#user.enabled}, :#{#user.auth});  \n" +
            //"       INSERT INTO authorities (username, authority) VALUES( :#{#user.username}, :#{#user.authority});  \n" +
            " END; ", nativeQuery = true)
    int insertUser(@Param("user") UserDTO user);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "BEGIN TRANSACTION; " +
    //        "DELETE FROM authorities WHERE username = ?1;" +
            "DELETE FROM users WHERE username = ?1; " +
            "END; ", nativeQuery = true)
    int deleteSelf(String e_mail);

    @Query(value = "SELECT * FROM \"users\" WHERE username = ?1", nativeQuery = true)
    Optional<User> getByEmail(String e_mail);

    @Query(value = "SELECT * FROM \"users\"", nativeQuery = true)
    List<User> getAll();

    @Query(value = "SELECT * FROM \"users\" WHERE username IN " +
            "SELECT username FROM authorities WHERE auth = ?1", nativeQuery = true)
    List<User> getAllByRole(String role);
}