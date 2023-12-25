package com.study.hexagonal.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;


    @Transactional
    public int decreaseMoneyPessimistic(String name, int money) {

        Account account = accountRepository.findByName(name);
        return account.decreaseMoney(money);
    }
}
