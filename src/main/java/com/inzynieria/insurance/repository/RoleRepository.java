package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface RoleRepository  extends JpaRepository<Role,Integer> {

}
