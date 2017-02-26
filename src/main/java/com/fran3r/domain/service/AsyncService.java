package com.fran3r.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Fran Alonso @ byteflair.com
 */
@Service
@Slf4j
public class AsyncService {
    @Async
    public void asyncMethodWithReturnType() {
        System.out.println("Execute method asynchronously - "
                               + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            LOGGER.info("Hello world I'm a asynchronous method!!");
        } catch (InterruptedException e) {
            //
        }
    }
}
