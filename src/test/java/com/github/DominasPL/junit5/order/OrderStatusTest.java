package com.github.DominasPL.junit5.order;

import com.github.DominasPL.junit5.order.OrderStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

class OrderStatusTest {

    @ParameterizedTest
    @EnumSource(OrderStatus.class)
    void allOrderStatusShouldBeShorterThan15Characters(OrderStatus orderStatus) {
        assertThat(orderStatus.toString().length(), lessThan(15));
    }
}