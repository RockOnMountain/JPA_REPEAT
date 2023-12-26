package com.study.hexagonal.ddd_project.order.event;

import com.study.hexagonal.ddd_project.order.domain.ShippingInfo;
import com.study.hexagonal.ddd_project.order.domain.ShippingInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class DeliveredEventListener {

    private final ShippingInfoRepository shippingInfoRepository;


//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @TransactionalEventListener
    // @EventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void sendShippingInfo(DeliveredEvent event) {

        try {
            Thread.sleep(1000);
        } catch(InterruptedException ignore) {}


        System.out.println("이벤트 발생");
        System.out.println("event Thread = " + Thread.currentThread().getId());

        ShippingInfo shippingInfo =
                ShippingInfo.builder().orderNumber(event.getOrderNumber()).address(event.getAddress())
                        .name(event.getName()).build();

        shippingInfoRepository.save(shippingInfo);

        throw new RuntimeException();
    }
}
