package com.github.DominasPL.junit5.order;

import com.github.DominasPL.junit5.Meal;
import com.github.DominasPL.junit5.order.Order;
import com.github.DominasPL.junit5.order.OrderBackup;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

class OrderBackupTest {

    private static OrderBackup orderBackup;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void addAtTheStartOfTheLine() throws IOException {
        orderBackup.getWriter().append("Order details: ");
    }

    @AfterEach
    void addAtTheEndOfTheLine() throws IOException {
        orderBackup.getWriter().append(" backed up!");
    }

    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeFile();
    }

    @Test
    void backUpOrderWithOneMeal() throws IOException {

        //given
        Meal meal = new Meal(1, "Fries");
        com.github.DominasPL.junit5.order.Order order = new Order();
        order.addMealToOrder(meal);

        //when
        orderBackup.backupOrder(order);

        //then
        System.out.println("Order " + order.toString());
    }
}