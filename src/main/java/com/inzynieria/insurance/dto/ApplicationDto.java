<<<<<<< HEAD
package com.inzynieria.insurance.dto;

import com.inzynieria.insurance.model.Application;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.jws.soap.SOAPBinding;
import java.sql.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ApplicationDto {
    public ApplicationDto(){}

    public ApplicationDto(Integer idApplication,String type, Date filling_date, String reason, Integer accepted, Integer user_idUser, String description){
        this.idApplication=idApplication;
        this.filling_date=filling_date;
        this.reason=reason;
        this.type=type;
        this.user_idUser=user_idUser;
        this.accepted=accepted;
        this.description=description;
    }



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Integer idApplication;
    private String type;
    private Date filling_date;
    private String reason;
    private Integer accepted;
    private Integer user_idUser;
    private String description;
}
=======
//package com.inzynieria.insurance.dto;
//
//public class ApplicationDto {
//
//    private Integer idapplication;
//    private String type;
//    private String filldate;
//    private String reason;
//
//    public ApplicationDto(){
//
//    }
//    public ApplicationDto(Integer idapplication, String type, String filldate , String reason){
//
//   // public Integer getIdapplication(); {
//    //    return idapplication;
//   // }
//
//    public void setIdapplication(Integer idapplication) {
//        this.idapplication = idapplication;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getFilldate() {
//        return filldate;
//    }
//
//    public void setFilldate(String filldate) {
//        this.filldate = filldate;
//    }
//
//    public String getReason() {
//        return reason;
//    }
//
//    public void setReason(String reason) {
//        this.reason = reason;
//    }
//
//
//
//
//}
>>>>>>> Wyszukiwanie offert
