package com.github.DominasPL.junit5;

import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
        Order order = new Order();
        order.addMealToOrder(meal);

        //when
        orderBackup.backupOrder(order);

        //then
        System.out.println("Order " + order.toString());
    }
}