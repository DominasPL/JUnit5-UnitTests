package com.github.DominasPL.junit5.cart;

import com.github.DominasPL.junit5.order.Order;
import com.github.DominasPL.junit5.order.OrderStatus;

public class CartService {

    private CartHandler cartHandler;

    public CartService(CartHandler cartHandler) {
        this.cartHandler = cartHandler;
    }

    public Cart processCart(Cart cart) {
        if (cartHandler.canHandleCart(cart)) {
            cartHandler.sendToPrepare(cart);
            cart.getOrders().forEach(order -> {
                order.changeOrderStatus(OrderStatus.PREPARING);
            });
            return cart;
        } else {
            cart.getOrders().forEach(order ->{
                order.changeOrderStatus(OrderStatus.REJECTED);
            });
            return cart;
        }
    }
}
