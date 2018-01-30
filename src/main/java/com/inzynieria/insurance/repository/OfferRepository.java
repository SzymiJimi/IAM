package com.inzynieria.insurance.repository;


import com.inzynieria.insurance.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
/**
 * Interfejs rozszerzający JpaRepository umożliwia pobieranie danych z encji offer z bazy danych w prosty i przejrzysty sposób.
 */
import java.util.List;

@Service
public interface OfferRepository extends JpaRepository<Offer,Integer> {
    /**
     * Wyszukiwanie ofert po nazwie
     * @param name nazwa ofert
     * @return Lista ofert
     */
    List<Offer> findOfferByName(String name);

}
