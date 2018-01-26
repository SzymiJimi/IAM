package com.inzynieria.insurance.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserRolesPK implements Serializable{

    private Integer USER_IDUSER;
    private Integer ROLE_IDROLE;

    public UserRolesPK(){

    }

    public UserRolesPK(Integer USER_IDUSER, Integer ROLE_IDROLE) {
        this.USER_IDUSER = USER_IDUSER;
        this.ROLE_IDROLE = ROLE_IDROLE;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
