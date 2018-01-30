package com.inzynieria.insurance.model;


import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Klasa modelowa, która odwzorowuje encję rola (role) w bazie danych. Jest ona niezbędna jeśli w projekcie chcemy korzystać z frameworka Hibrenate.
 */
@Data
@Entity
public class Role {


    private Integer idRole;
    private String name;
    private Set<Command> commands = new HashSet<>(0);
    private Set<User> users = new HashSet<>(0);


    public Role ()
    {

    }
    public Role (Integer idRole, String name)
    {
        this.idRole= idRole;
        this.name=name;
    }

    public Role (String name)
    {
        this.name=name;
    }

    public Role (String name, Set<Command> commands)
    {
        this.name=name;
        this.commands= commands;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.role", cascade=CascadeType.ALL)

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "RoleCommands", joinColumns = @JoinColumn(name = "ROLE_IDROLE", referencedColumnName = "idRole"), inverseJoinColumns = @JoinColumn(name = "COMMAND_IDCOMMAND", referencedColumnName = "idCommand"))
    public Set<Command> getCommands() {
        return this.commands;
    }

    public void setCommands(Set<Command> commands) {
        this.commands = commands;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        String result = String.format(
                "Role [idRole=%d, name='%s']%n",
                idRole, name);
        if (commands != null) {
            for(Command command : commands) {
                result += String.format(
                        "Command[idCommand=%d, name='%s']%n",
                        command.getIdCommand(), command.getName());
            }
        }

        return result;
    }

}
