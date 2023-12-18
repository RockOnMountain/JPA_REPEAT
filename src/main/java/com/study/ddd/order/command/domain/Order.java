package com.study.ddd.order.command.domain;

import java.util.List;
import java.util.Objects;
import org.springframework.util.CollectionUtils;

public class Order {

    private OrderNumber id; // 식별자

    private OrderState orderState;
    private List<OrderLine> orderLines;
    private Orderer orderer;
    private ShippingInfo shippingInfo;
    private int totalAmounts;


    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {

        this.setOrderLines(orderLines);
        this.setShippingInfo(shippingInfo);
    }


    public void setShippingInfo(ShippingInfo shippingInfo) {

        if(Objects.isNull(shippingInfo)) {
            throw new IllegalArgumentException("no ShippingInfo exist");
        }

        this.shippingInfo = shippingInfo;
    }


    public void changeShippingInfo(ShippingInfo newShippingInfo) {

        this.verifyNotYetShipped();
        this.setShippingInfo(newShippingInfo);
    }


    private void verifyNotYetShipped() {

        if(orderState != OrderState.PAYMENT_WAITING && orderState != OrderState.PREPARING) {
            throw new IllegalStateException("already shipped");
        }
    }


    private void setOrderLines(List<OrderLine> orderLines) {

        this.verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        this.calculateTotalAmounts();
    }


    private void calculateTotalAmounts() {
        this.totalAmounts = orderLines.stream().mapToInt(OrderLine::getAmounts).sum();
    }


    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {

        if(CollectionUtils.isEmpty(orderLines)) {
            throw new IllegalArgumentException("no orderLine exist");
        }
    }


    public void cancel() {
        this.verifyNotYetShipped();
        this.orderState = OrderState.CANCELED;
    }


    private void setOrderer(Orderer orderer) {

        if(Objects.isNull(orderer)) {
            throw new IllegalArgumentException("no orderer");
        }

        this.orderer = orderer;
    }


}
