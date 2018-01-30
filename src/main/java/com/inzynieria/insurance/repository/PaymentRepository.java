package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payments,Integer> {
}
