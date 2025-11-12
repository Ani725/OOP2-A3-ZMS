package com.example.assign3zms;

public class Tiger extends Animal {

    public Tiger(String name, int age) {
            super(name, age);
    }

    @Override
    public String toString() {
        return "Tiger: " + getName() + " (Age: " + getAge() + ")";
    }
}
