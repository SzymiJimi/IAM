package com.inzynieria.insurance.model;


import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Klasa modelowa, która odwzorowuje encję command w bazie danych. Jest ona niezbędna jeśli w projekcie chcemy korzystać z frameworka Hibrenate.
 */
@Data
@Entity
public class Command {


    private Integer idCommand;
    private String name;
    private Set<Role> roles = new HashSet<>(0);

    public Command (){

    }
    public Command( Integer idCommand, String name)
    {
        this.idCommand= idCommand;
        this.name= name;
    }
    public Command( String name)
    {
        this.name= name;
    }

    public Command( String name, Set<Role> roles)
    {
        this.name= name;
        this.roles= roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(Integer idCommand) {
        this.idCommand = idCommand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.command")

    @ManyToMany(mappedBy = "commands")
    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
