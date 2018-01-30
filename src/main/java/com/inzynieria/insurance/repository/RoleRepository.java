package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
/**
 * Interfejs rozszerzający JpaRepository umożliwia pobieranie danych z role z bazy danych w prosty i przejrzysty sposób.
 */
@Service
public interface RoleRepository  extends JpaRepository<Role,Integer> {

}
