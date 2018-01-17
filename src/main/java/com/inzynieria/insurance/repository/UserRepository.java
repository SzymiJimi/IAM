package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.User;
import javafx.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByName(String name);
    User findByUsername(String username);
    List<User> findUserByNameOrSurnameOrUsernameOrEmail(String name, String surname, String username, String Email);

    @Query(value= "SELECT  u.idUser, u.username, u.password, u.name, u.surname, u.email, u.idRole from User u, UserRoles, role where u.idUser=userRoles.USER_IDUSER AND userRoles.ROLE_IDROLE=role.idRole AND  ( u.username= :data OR u.name= :data OR u.surname= :data OR u.email= :data)  AND role.name = 'ROLE_CLIENT'", nativeQuery = true)
    List<User> findClient(@Param("data") String data);

}
