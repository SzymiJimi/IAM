package com.inzynieria.insurance.dto;


import com.inzynieria.insurance.model.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDto {

    private Integer idUser;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private List<RoleDto> roles;

    public UserDto(){

    }

    public UserDto(Integer idUser, String username, String password ,String name, String surname, String email) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public UserDto(Integer idUser, String username, String password ,String name, String surname, String email, List<RoleDto> roles) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
    }

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

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> role) {
        this.roles = role;
    }


}
