package com.github.DominasPL.junit5.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository {


    @Override
    public List<Account> getAllAccounts() {

        Address address1 = new Address("Sienkeiwczia", "34g");
        Account account1 = new Account(address1);

        Address address2 = new Address("Stepucha", "35d");
        Account account2 = new Account(address2);

        Account account3 = new Account();


        return Arrays.asList(account1, account2, account3);
    }
}
