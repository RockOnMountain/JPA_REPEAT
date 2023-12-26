package com.study.hexagonal.ddd_project.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderNumber(String orderNumber);
}
