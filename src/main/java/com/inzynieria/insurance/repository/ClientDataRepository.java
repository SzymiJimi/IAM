package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.ClientData;
import com.inzynieria.insurance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientDataRepository extends JpaRepository<ClientData,Integer> {

    ClientData findClientDataByUserId(Integer id);

    @Query(value= "SELECT  c.idClientData, c.userId, c.idNumber, c.pesel, c.phone, c.place, c.houseNr, c.flatNr, c.street from ClientData c, User,  UserRoles, Role where c.userId=User.idUser AND User.idUser=userRoles.USER_IDUSER AND userRoles.ROLE_IDROLE=Role.idRole  AND role.name = 'ROLE_CLIENT'", nativeQuery = true)
    List<ClientData> findAllClients();

}
