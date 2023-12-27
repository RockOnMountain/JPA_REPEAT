package com.study.hexagonal.hexagonal_project.policy;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PriceServiceTest {


    @Test
    @DisplayName("grade discount")
    void gradeDiscount() {

        // given
        Customer customer = new Customer("박종혁", CustomerGrade.GOLD);
        Product product = new Product("상품 1", 10000);

        // when
        PriceService priceService = new PriceService(new GradeDiscountPolicy());

        // then
        assertThat(priceService.discount(customer, product)).isEqualTo(5000);

    }


    @Test
    @DisplayName("price discount")
    void priceDiscount() {

        // given
        Customer customer = new Customer("박종혁", CustomerGrade.GOLD);
        Product product = new Product("상품 1", 21000);

        // when
        PriceService priceService = new PriceService(new PriceDiscountPolicy());

        // then
        assertThat(priceService.discount(customer, product)).isEqualTo(19000);


    }
}