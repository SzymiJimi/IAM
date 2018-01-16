package com.inzynieria.insurance.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idApplication;
    private Date filling_date;
    private String reason;
    private String type;
    private Integer user_idUser;
    private Integer accepted;

    public Application() {}


    public Integer getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(Integer idApplication) {
        this.idApplication = idApplication;
    }

    public Date getFilling_date() {
        return filling_date;
    }

    public void setFilling_date(Date filling_date) {
        this.filling_date = filling_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUser_idUser() {
        return user_idUser;
    }

    public void setUser_idUser(Integer user_idUser) {
        this.user_idUser = user_idUser;
    }

    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }
}
