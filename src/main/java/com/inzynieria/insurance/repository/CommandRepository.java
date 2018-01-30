package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
/**
 * Interfejs rozszerzający JpaRepository umożliwia pobieranie danych z encji command z bazy danych w prosty i przejrzysty sposób.
 */
@Service
public interface CommandRepository  extends JpaRepository<Command,Integer> {
}
