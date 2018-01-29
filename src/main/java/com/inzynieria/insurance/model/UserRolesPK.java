package com.inzynieria.insurance.model;


import java.io.Serializable;

/**
 * Klasa słuząca do tworzenia kluczy głownych dla tabeli pośredniej UserRoles.
 */
public class UserRolesPK implements Serializable{

    /**
     * Pole odnosi się do klucza głównego w tabeli User.
     */
    private Integer USER_IDUSER;
    /**
     * Pole odnosi się do klucza głównego w tabeli Role.
     */
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
