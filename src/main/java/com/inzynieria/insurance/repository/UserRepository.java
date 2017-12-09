package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByName(String name);
    List<User> findUserByNameOrSurnameOrUsernameOrEmail(String name, String surname, String username, String Email);
}
