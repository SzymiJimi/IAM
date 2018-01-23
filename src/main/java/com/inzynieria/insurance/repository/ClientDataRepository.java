package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.ClientData;
import com.inzynieria.insurance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientDataRepository extends JpaRepository<ClientData,Integer> {

    ClientData findClientDataByUserId(Integer id);

}
