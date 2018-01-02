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

    public ApplicationDto(Integer idApplication, Date filling_date, String reason, String type, Integer user_UserId, Integer accepted){
        this.idApplication=idApplication;
        this.filling_date=filling_date;
        this.reason=reason;
        this.type=type;
        this.user_UserId= user_UserId;
        this.accepted=accepted;
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

    public Integer getUser_UserId() {
        return user_UserId;
    }

    public void setUser_UserId(Integer user_UserId) {
        this.user_UserId = user_UserId;
    }

    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    private Integer idApplication;
    private Date filling_date;
    private String reason;
    private String type;
    private Integer user_UserId;
    private Integer accepted;
}
