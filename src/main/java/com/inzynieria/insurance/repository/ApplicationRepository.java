package com.inzynieria.insurance.repository;


import com.inzynieria.insurance.model.Application;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationRepository extends JpaRepository<Application,Integer> {

    List<Application> findApplicationByType(String type);

    @Query(value= "select role.name from user, role, userRoles where user.idUser=userRoles.USER_IDUSER and userRoles.ROLE_IDROLE=role.idRole AND username = :data", nativeQuery = true)
    List <String> roleForFind(@Param("data") String data);

}
