package com.inzynieria.insurance.repository;


import com.inzynieria.insurance.model.Application;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationRepository extends JpaRepository<Application,Integer> {

    List<Application> findApplicationByType(String type);

}
