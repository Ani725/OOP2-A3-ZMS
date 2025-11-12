package com.example.assign3zms.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Enclosure {
    private final String name;
    private final List<Animal> animals = new ArrayList<>();

    public Enclosure(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    public void addAnimal(Animal a) {
        animals.add(a);
    }

    public boolean removeAnimal(Animal a) {
        return animals.remove(a);
    }

    public void clear() {
        animals.clear();
    }
}
