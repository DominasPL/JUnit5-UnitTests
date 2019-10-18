package com.github.DominasPL.junit5;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {

        //given
        Meal meal = new Meal(35);

        //then
        int discountedPrice = meal.getDiscountedPrice(10);

        //then
        assertEquals(25, discountedPrice);
        assertThat(discountedPrice, equalTo(25));
        assertThat(discountedPrice, is(25));
    }

    @Test
    void referencesToTheSameShouldBeEqual() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);
        assertThat(meal1, sameInstance(meal2));
    }

    @Test
    void referencesToDifferentObjectsShouldNotBeEqual() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

        //then
        assertNotSame(meal1, meal2);
        assertThat(meal1, not(sameInstance(meal2)));
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {

        //given
        Meal meal1 = new Meal(10, "Watermelon");
        Meal meal2 = new Meal(10, "Watermelon");

        //then
        assertEquals(meal1, meal2);
        assertThat(meal1, equalToObject(meal2));
    }
}