package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContractRepository extends JpaRepository<Contract,Integer> {

    List<Contract> findContractByIdUser(Integer id);

    @Query(value= "SELECT  c.idContract, c.expirationDate, c.idOffer, c.idUser, c.startDate from contract c, clientdata where c.idUser= clientData.userId AND  clientdata.pesel= :data", nativeQuery = true)
    List<Contract> findContractByPesel(@Param("data") String data);

}
