package com.inzynieria.insurance.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Klasa modelowa, która odwzorowuje encję ról użytkownika (userRoles) w bazie danych. Jest ona niezbędna jeśli w projekcie chcemy korzystać z frameworka Hibrenate.
 */
@Data
@Entity
@IdClass(value=UserRolesPK.class)
public class UserRoles implements Serializable {

    public UserRoles(){

    }

    public UserRoles(String name, Integer USER_IDUSER, Integer ROLE_IDROLE) {
        this.name = name;
        this.USER_IDUSER = USER_IDUSER;
        this.ROLE_IDROLE = ROLE_IDROLE;
    }

    private String name;

    @Id
    private Integer USER_IDUSER;

    @Id
    private Integer ROLE_IDROLE;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getUSER_IDUSER() {
        return USER_IDUSER;
    }

    public void setUSER_IDUSER(Integer USER_IDUSER) {
        this.USER_IDUSER = USER_IDUSER;
    }

    public Integer getROLE_IDROLE() {
        return ROLE_IDROLE;
    }

    public void setROLE_IDROLE(Integer ROLE_IDROLE) {
        this.ROLE_IDROLE = ROLE_IDROLE;
    }
}
