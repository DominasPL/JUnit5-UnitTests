package com.github.DominasPL.junit5.account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    List<Account> getAllActiveAccounts() {
        return accountRepository.getAllAccounts().stream()
                .filter(account -> account.isActive())
                .collect(Collectors.toList());
    }


}
