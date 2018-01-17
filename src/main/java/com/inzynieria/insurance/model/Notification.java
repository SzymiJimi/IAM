package com.inzynieria.insurance.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idNotification;
    private String type;
    private String status;
    private String incident_time;
    private String incident_description;
    private String valuation;
    private Integer idContract;

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIncident_time() {
        return incident_time;
    }

    public void setIncident_time(String incident_time) {
        this.incident_time = incident_time;
    }

    public String getIncident_description() {
        return incident_description;
    }

    public void setIncident_description(String incident_description) {
        this.incident_description = incident_description;
    }

    public String getValuation() {
        return valuation;
    }

    public void setValuation(String valuation) {
        this.valuation = valuation;
    }

    public Integer getIdContract() {
        return idContract;
    }

    public void setIdContract(Integer idContract) {
        this.idContract = idContract;
    }
}
