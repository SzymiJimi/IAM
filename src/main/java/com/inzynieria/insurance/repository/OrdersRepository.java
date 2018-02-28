package com.inzynieria.insurance.repository;

import com.inzynieria.insurance.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
}
