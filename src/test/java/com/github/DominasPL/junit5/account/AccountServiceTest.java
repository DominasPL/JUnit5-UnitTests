package com.github.DominasPL.junit5.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    void getAllActiveAccounts() {

        //given
        AccountRepositoryStub accountRepositoryStub = new AccountRepositoryStub();
        AccountService accountService = new AccountService(accountRepositoryStub);

        //when
        List<Account> accounts = accountService.getAllActiveAccounts();

        //then
        assertThat(accounts, hasSize(2));
    }
}