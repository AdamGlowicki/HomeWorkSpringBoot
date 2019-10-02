package com.glowicki.homework3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {

    private long id;
    private String mark;
    private String model;
    private String color;

    public Car(long id, String mark, String model, String color) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }
}
