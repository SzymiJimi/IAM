package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationRepository extends JpaRepository<Notification,Integer> {

    @Query(value= "SELECT  n.idNotification, n.type, n.status, n.incident_time, n.incident_description, n.valuation, n.idContract from Notification n, contract,user, clientdata where n.idContract=contract.idContract AND contract.idUser=user.idUser AND user.idUser=clientData.userId AND  clientdata.pesel=:data", nativeQuery = true)
    List<Notification> findNotificationsByUserPesel(@Param("data") String data);
}