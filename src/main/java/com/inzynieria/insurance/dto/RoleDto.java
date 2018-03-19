package com.inzynieria.insurance.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Uproszczona werja modelu Role z bazy danych. Różni się tym od klasy modelowej Role tym, że klasa ta nie posiada żadnych odniesień do encji command i user.
 * Uławia to sparsowanie obiektu do JSONa.
 */
@Data
public class RoleDto {

    private Integer idRole;
    private String name;


    public RoleDto ()
    {

    }

    public RoleDto (String name)
    {
        this.name=name;
    }

    public RoleDto (Integer idRole,  String name)
    {
        this.idRole=idRole;
        this.name=name;
    }


    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
