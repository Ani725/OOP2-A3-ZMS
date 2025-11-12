package com.example.assign3zms.Model;

/**
 * Represents a basic animal in the Zoo Management System.
 * <p>
 * Each animal has a name and an age. This class serves as a base model
 * for specific animal types (e.g., Tiger, Lion, etc.) and provides
 * simple getters, setters, and a formatted string representation.
 * </p>
 *
 * @author Alejandro
 */
public class Animal {

    /** The name of the animal. */
    private String name;

    /** The age of the animal. */
    private int age;

    /**
     * Constructs a new Animal with the given name and age.
     *
     * @param name The name of the animal.
     * @param age  The age of the animal.
     */
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the name of the animal.
     *
     * @return The animal's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the animal.
     *
     * @param name The new name of the animal.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the age of the animal.
     *
     * @return The animal's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the animal.
     *
     * @param age The new age of the animal.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns a formatted string representation of the animal.
     * <p>
     * Example: {@code "Simba (5)"}
     * </p>
     *
     * @return A string containing the animal's name and age.
     */
    @Override
    public String toString() {
        return String.format("%s (%d)", name, age);
    }
}
