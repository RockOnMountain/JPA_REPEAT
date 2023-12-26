package com.study.hexagonal.ddd_project.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Long> {

    ShippingInfo findByOrderNumber(String orderNumber);
}
