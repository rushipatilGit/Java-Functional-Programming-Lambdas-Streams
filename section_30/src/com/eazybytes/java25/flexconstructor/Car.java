package com.eazybytes.java25.flexconstructor;

public class Car extends Vehicle {

    Car(int speed) {
        if (speed < 0) throw new IllegalArgumentException("Invalid speed");
        if (speed > 200) throw new IllegalArgumentException("Car too fast");
        super(speed); // Forced first call
    }

    private static int verifySpeed(int speed) {
        if (speed < 0) throw new IllegalArgumentException("Invalid speed");
        if (speed > 200) throw new IllegalArgumentException("Car too fast");
        return speed;
    }


}
