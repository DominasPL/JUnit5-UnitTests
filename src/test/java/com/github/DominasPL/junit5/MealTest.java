package com.github.DominasPL.junit5;

import com.github.DominasPL.junit5.extensions.IaExceptionIgnoreExtension;
import com.github.DominasPL.junit5.order.Order;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

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

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice() {

        //given
        Meal meal = new Meal(8, "Sum");

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(10));

    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 18})
    void mealPricesShouldBeLowerThan20(int price) {
        assertThat(price, lessThan(20));
    }

    @Tag("hamburger")
    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgerShouldHaveCorrectNameAndPrice(String name, int price) {

        assertThat(name, containsString("burger"));
        assertThat(price, greaterThan(8));
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name) {
        assertThat(name, endsWithIgnoringCase("cake"));
        assertThat(name, notNullValue());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,3,5,8})
    @ExtendWith(IaExceptionIgnoreExtension.class)
    void mealPricesShouldBeLowerThan10(int price) {
        if (price > 5) {
            throw new IllegalArgumentException();
        }
        assertThat(price, lessThan(20));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    private static Stream<String> createCakeNames() {
        List<String> cakeNames = Arrays.asList("CheeseCake", "FruitCake", "AppleCake");
        return cakeNames.stream();
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestCollection() {
        return Arrays.asList(
                dynamicTest("Dynamic test1", () -> assertThat(5, lessThan(6))),
                dynamicTest("Dynamic test2", () -> assertEquals(4, 2*2))
        );
    }

    @Tag("hamburger")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices() {

        Order order = new Order();
        order.addMealToOrder(new Meal(10, "Hamburger", 2));
        order.addMealToOrder(new Meal(2, "Watermelon", 3));
        order.addMealToOrder(new Meal(1, "Pineapple", 4));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(25));
            };
            String name = "Test name " + i;

            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }


        return dynamicTests;

    }

    private int calculatePrice(int price, int quantity) {
        return price*quantity;
    }


}