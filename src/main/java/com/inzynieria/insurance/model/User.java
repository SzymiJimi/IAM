package com.inzynieria.insurance.model;

import com.inzynieria.insurance.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

    private Integer idUser;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Integer idRole;
    private Set<Role> roles = new HashSet<>(0);



    public User(Integer idUser, String username, String password, String name, String surname, String email) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.idRole = idRole;
    }

    public User(){
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }




    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "UserRoles", joinColumns = @JoinColumn(name = "USER_IDUSER", referencedColumnName = "idUser"), inverseJoinColumns = @JoinColumn(name = "ROLE_IDROLE", referencedColumnName = "idRole"))
    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        String result = String.format(
                "User [idUser=%d, name='%s']%n",
                idUser, name);
        if (roles != null) {
            for(Role role : roles) {
                result += String.format(
                        "Role[idRole=%d, name='%s']%n",
                        role.getIdRole(), role.getName());
            }
        }

        return result;
    }

}
