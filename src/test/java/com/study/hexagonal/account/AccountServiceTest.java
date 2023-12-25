package com.study.hexagonal.account;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;


    @Test
    void decreasePessimistic() throws InterruptedException {

        // given
        accountRepository.save(new Account("박종혁", 20000));
        AtomicInteger successCount = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(200);

        // when
        // Thread 생성
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        IntStream.range(0, 200).forEach(index -> {
            executorService.execute(() -> {
                try {
                    System.out.println(accountService.decreaseMoneyPessimistic("박종혁", 1000));
                    successCount.getAndIncrement();
                } catch(ObjectOptimisticLockingFailureException e) {
                    System.out.println("충돌감지");
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                countDownLatch.countDown();
            });
        });

        // countDownLatch 가 0이 될 때까지 메인 스레드는 아래의 코드를 실행하지 않는다.
        countDownLatch.await();

        // then
        assertThat(successCount.get()).isEqualTo(20);
    }
}