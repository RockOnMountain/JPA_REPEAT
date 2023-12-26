package com.study.hexagonal.ddd_project.order.application;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.study.hexagonal.ddd_project.order.domain.Order;
import com.study.hexagonal.ddd_project.order.domain.OrderRepository;
import com.study.hexagonal.ddd_project.order.domain.Orderer;
import com.study.hexagonal.ddd_project.order.domain.ShippingInfo;
import com.study.hexagonal.ddd_project.order.domain.ShippingInfoRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    ShippingInfoRepository shippingInfoRepository;
    @Autowired
    OrderRepository orderRepository;


    @Test
    @DisplayName("예약하기 - 배송 이벤트")
    void order() {

        // given
        String orderNumber = "testOrderNumber";
        Orderer orderer = new Orderer("park", "jeus12@gmail.com", "seoul");

        // when
        orderService.order(orderNumber, orderer);

        // then
        ShippingInfo shippingInfo = shippingInfoRepository.findByOrderNumber(orderNumber);
        assertThat(shippingInfo.getOrderNumber()).isEqualTo(orderNumber);
        assertThat(shippingInfo.getAddress()).isEqualTo(orderer.getAddress());
        assertThat(shippingInfo.getName()).isEqualTo(orderer.getName());
    }


    @Test
    @DisplayName("예약하기 - 실패 후 롤백 되는지")
    void orderFail() throws InterruptedException {

        // given
        String orderNumber = "testOrderNumber";
        Orderer orderer = new Orderer("park", "jeus12@gmail.com", "seoul");

        // when
        orderService.order(orderNumber, orderer);


        Order findOrder = orderRepository.findByOrderNumber(orderNumber);
        System.out.println("order = " + findOrder);
//        await().pollInterval(Duration.ofMillis(500)).until(() -> {
//             ShippingInfo shippingInfo = shippingInfoRepository.findByOrderNumber(orderNumber);
//             return shippingInfo != null;
//        });

        // await().atMost(Duration.ofSeconds(2));
        Thread.sleep(2000);
        ShippingInfo shippingInfo = shippingInfoRepository.findByOrderNumber(orderNumber);
        System.out.println("shippinInfo = " + shippingInfo);


//        try {
//            orderService.order(orderNumber, orderer);
//        } catch(RuntimeException e) {
//            Order findOrder = orderRepository.findByOrderNumber(orderNumber);
//            System.out.println("order = " + findOrder);
//            ShippingInfo shippingInfo = shippingInfoRepository.findByOrderNumber(orderNumber);
//            System.out.println("shippingInfo = " + shippingInfo);
//        }

    }


}