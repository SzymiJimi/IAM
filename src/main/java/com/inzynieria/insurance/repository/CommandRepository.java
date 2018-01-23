package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CommandRepository  extends JpaRepository<Command,Integer> {
}
