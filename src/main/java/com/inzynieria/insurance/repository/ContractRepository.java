package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContractRepository extends JpaRepository<Contract,Integer> {

    List<Contract> findContractByIdUser(Integer id);

}
