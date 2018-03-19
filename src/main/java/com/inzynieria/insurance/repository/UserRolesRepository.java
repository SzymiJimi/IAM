package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.UserRoles;
import com.inzynieria.insurance.model.UserRolesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Interfejs rozszerzający JpaRepository umożliwia pobieranie danych z encji userroles z bazy danych w prosty i przejrzysty sposób.
 */
public interface UserRolesRepository extends JpaRepository<UserRoles,Integer> {

        @Transactional
        @Modifying
        @Query(value= "delete from UserRoles where UserRoles.USER_IDUSER=:data", nativeQuery = true)
        Integer deleteData(@Param(value = "data") Integer data);

//    UserRoles findOneByUserid(UserRolesPK Id);


//    @Transactional
//    UserRoles deleteUserRolesByUSER_IDUSER(Integer USER_IDUSER);

}
