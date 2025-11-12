package com.example.assign3zms.Model;

/**
 * Represents a Tiger in the Zoo Management System.
 * <p>
 * The Tiger class extends the {@link Animal} class and inherits
 * its basic properties such as name and age. It overrides the
 * {@code toString()} method to provide a formatted string specific
 * to tigers.
 * </p>
 *
 * @author Alejandro
 */
public class Tiger extends Animal {

    /**
     * Constructs a new Tiger with the specified name and age.
     *
     * @param name The name of the tiger.
     * @param age  The age of the tiger.
     */
    public Tiger(String name, int age) {
        super(name, age);
    }

    /**
     * Returns a formatted string representation of the tiger.
     * <p>
     * Example: {@code "Tiger: Rajah (Age: 5)"}
     * </p>
     *
     * @return A string containing the tiger's name and age.
     */
    @Override
    public String toString() {
        return "Tiger: " + getName() + " (Age: " + getAge() + ")";
    }
}
