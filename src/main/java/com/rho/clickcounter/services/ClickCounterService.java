package com.rho.clickcounter.services;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Hugo Seabra (hugo.d.seabra@gmail.com)
 */
public class ClickCounterService {
    private static volatile ClickCounterService instance;
    private AtomicInteger counter;

    private ClickCounterService() {
        counter = new AtomicInteger(0);
    }

    public static ClickCounterService getInstance() {
        if (instance == null) {
            synchronized (ClickCounterService.class) {
                if (instance == null) {
                    instance = new ClickCounterService();
                }
            }
        }
        return instance;
    }

    public Integer incrementClickCounter() {
        return this.counter.incrementAndGet();
    }

    public Integer getClickCounter() {
        return this.counter.get();
    }
}
