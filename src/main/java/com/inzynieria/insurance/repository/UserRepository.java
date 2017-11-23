package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends CrudRepository<User,Integer> {
}
