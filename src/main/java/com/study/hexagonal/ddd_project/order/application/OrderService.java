package com.study.hexagonal.ddd_project.order.application;

import com.study.hexagonal.ddd_project.order.domain.Order;
import com.study.hexagonal.ddd_project.order.domain.OrderRepository;
import com.study.hexagonal.ddd_project.order.domain.Orderer;
import com.study.hexagonal.ddd_project.order.event.DeliveredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ApplicationEventPublisher eventPublisher;
    private final OrderRepository orderRepository;


    @Transactional
    public void order(String orderNumber, Orderer orderer) {

        Order order = new Order(orderNumber, orderer);
        orderRepository.save(order);
        order.order();
        eventPublisher.publishEvent(new DeliveredEvent(order.getOrderNumber(), order.getOrderer()));
    }
}
