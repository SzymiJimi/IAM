package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interfejs rozszerzający JpaRepository umożliwia pobieranie danych z encji userroles z bazy danych w prosty i przejrzysty sposób.
 */
public interface UserRolesRepository extends JpaRepository<UserRoles,Integer> {

}
