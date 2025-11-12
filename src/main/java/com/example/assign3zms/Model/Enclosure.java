package com.example.assign3zms.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * An enclosure that holds a list of {@link Animal} objects.
 * <p>
 * This class implements {@link EnclosureCollection}. Since this is a leaf
 * enclosure (not a composite).
 *
 * <p>Fields are intentionally final to indicate that the enclosure name and
 * the backing animal list reference to do not change after construction. The
 * backing list itself is mutable and can have animals added/removed.
 * 
 * @author Dieudonne
 */
public class Enclosure implements EnclosureCollection{

    /** The enclosure's name (immutable after construction). */
    private final String aName;

    /** Mutable list that stores animals contained in this enclosure. */
    private final List<Animal> aAnimal;

    /**
     * Create a new enclosure with the provided name.
     *
     * @param pName the display name for this enclosure; must not be null
     */
    public Enclosure(String pName) {
        try {
            if (pName == null) {
                throw new IllegalArgumentException("Enclosure name cannot be null.");
            }
            this.aName = pName;
            this.aAnimal = new ArrayList<>();
        } catch (IllegalArgumentException e) {
            System.out.println("Alert: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Add an animal to this enclosure.
     *
     * @param pAnimal the animal to add; must not be null
     */
    public void addAnimal(Animal pAnimal) {
        try {
            if (pAnimal == null) {
                throw new NullPointerException("Cannot add null animal to enclosure.");
            }
            this.aAnimal.add(pAnimal);
        } catch (NullPointerException e) {
            System.out.println("Alert: " + e.getMessage());
        }
    }

    /**
     * Remove an animal from this enclosure.
     * If the animal is not present, this method does nothing.
     *
     * @param pAnimal the animal to remove; must not be null
     */
    public void deleteAnimal(Animal pAnimal){
        if (pAnimal == null) {
            throw new NullPointerException("Cannot remove zero animal from enclosure.");
        }
        this.aAnimal.remove(pAnimal); 
    }

    /**
     * Get the enclosure's name.
     *
     * @return the name supplied at construction
     */
    @Override
    public String getName() {
        return this.aName;
    }

    public List<Animal> getAnimals() {
        return this.aAnimal;
    }
}