package com.github.DominasPL.junit5.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Sienkiewicza, 10d", "Armii krajowej, 30c", "Swistackiego, 14/32"})
    void givenAddressesShouldNotBeEmptyAndHaveProperNames(String street, String number) {

        assertThat(street, notNullValue());
        assertThat(street, containsStringIgnoringCase("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(10));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void givenAddressesFromFileShouldNotBeEmptyAndHaveProperNames(String street, String number) {

        assertThat(street, notNullValue());
        assertThat(street, containsStringIgnoringCase("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(10));
    }



}