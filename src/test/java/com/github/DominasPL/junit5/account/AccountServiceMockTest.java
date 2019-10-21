package com.github.DominasPL.junit5.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceMockTest {


    @Test
    void getAllActiveAccounts() {

        //given
        List<Account> accountList = prepareAccountData();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        when(accountRepository.getAllAccounts()).thenReturn(accountList);

        //when
        List<Account> accounts = accountService.getAllActiveAccounts();

        //then
        assertThat(accounts, hasSize(2));
    }

    @Test
    void getNoActiveAccounts() {

        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        when(accountRepository.getAllAccounts()).thenReturn(Collections.emptyList());

        //when
        List<Account> accounts = accountService.getAllActiveAccounts();

        //then
        assertThat(accounts, hasSize(0));
    }



    public List<Account> prepareAccountData() {

        Address address1 = new Address("Sienkeiwczia", "34g");
        Account account1 = new Account(address1);

        Address address2 = new Address("Stepucha", "35d");
        Account account2 = new Account(address2);

        Account account3 = new Account();


        return Arrays.asList(account1, account2, account3);
    }

}
