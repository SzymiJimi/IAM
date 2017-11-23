package com.inzynieria.insurance.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDto {

    private Integer idUser;

    private String username;
    private String name;
    private String surname;
    private String email;
    private String role;

    public UserDto(){

    }

    public UserDto(Integer idUser, String username, String name, String surname, String email, String role) {
        this.idUser = idUser;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
