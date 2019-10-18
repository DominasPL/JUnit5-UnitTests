package com.github.DominasPL.junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;



    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @AfterEach
    void cleanUp() {
        order.cancel();
    }

    @Test
    void testAssertArrayEquals() {
        //given
        int[] numbers1 = {1, 2, 4, 5};
        int[] numbers2 = {1, 2, 4, 5};

        //then
        assertArrayEquals(numbers1, numbers2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder() {
        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals().size(), is(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize() {
        //given
        Meal meal1 = new Meal(10, "Burger");
        Meal meal2 = new Meal(2, "Sandwich");

        //when
        order.addMealToOrder(meal1);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), not(empty()));
        assertThat(order.getMeals(), contains(meal1));
        assertThat(order.getMeals(), hasItem(meal1));
        assertThat(order.getMeals().get(0).getPrice(), equalTo(10));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {
        //given
        Meal meal1 = new Meal(10, "Burger");

        //when
        order.addMealToOrder(meal1);
        order.removeMealFromOrder(meal1);

        //then
        assertThat(order.getMeals().size(), is(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal1)));
        assertThat(order.getMeals(), empty());
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {
        //given
        Meal meal1 = new Meal(10, "Burger");
        Meal meal2 = new Meal(1, "Ice cream");

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(), contains(meal1, meal2));
        assertThat(order.getMeals(), containsInAnyOrder(meal2, meal1));
    }

    @Test
    void testIfTwoMealListAreTheSame() {
        //given
        Meal meal1 = new Meal(10, "Burger");
        Meal meal2 = new Meal(1, "Ice cream");
        Meal meal3 = new Meal(2, "Kebab");

        //when
        List<Meal> meals1 = Arrays.asList(meal1, meal2, meal3);
        List<Meal> meals2 = Arrays.asList(meal1, meal2, meal3);

        //then
        assertThat(meals1, is(meals2));
    }
}