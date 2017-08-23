package com.rho.clickcounter.rest.dto;

/**
 * @author Hugo Seabra (hugo.d.seabra@gmail.com)
 */
public class ClickCounterEntity {
    private Integer counter;

    public ClickCounterEntity() {
    }

    public ClickCounterEntity(Integer counter) {
        this.counter = counter;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
