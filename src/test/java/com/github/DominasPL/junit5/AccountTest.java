package com.github.DominasPL.junit5;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation() {
        //given
        //when
        Account account = new Account();
        //then
        assertFalse(account.isActive());
        assertThat(account.isActive(), equalTo(false));
        assertThat(account.isActive(), is(false));
    }

    @Test
    void accountShouldBeActiveAfterActivation() {
        //given
        Account account = new Account();

        //when
        account.activate();

        //then
        assertTrue(account.isActive());
        assertThat(account.isActive(), equalTo(true));
        assertThat(account.isActive(), is(true));
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet() {

        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address, nullValue());
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {
        //given
        Address address = new Address("Wyszynskiego", "63d");
        Account account = new Account();

        //when
        account.setDefaultDeliveryAddress(address);
        Address accountAddress = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(accountAddress);
        assertThat(accountAddress, is(notNullValue()));
    }

    @Test
    @RepeatedTest(10)
    void newAccountWithNotNullAddressShouldBeActive() {

        //given
        Address address = new Address("SIenkiewczia", "14d");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
        });
    }
}