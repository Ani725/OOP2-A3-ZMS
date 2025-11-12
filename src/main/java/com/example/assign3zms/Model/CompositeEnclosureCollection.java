package com.example.assign3zms.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a composite collection of enclosures or subsections in the zoo.
 * Can contain multiple Enclosures or other CompositeEnclosureCollections.
 * Implements EnclosureCollection interface.
 *
 * Author: Rohina
 */
public class CompositeEnclosureCollection implements EnclosureCollection {

    /**
     * Name of the area/section (e.g., "Big Cats")
     */
    private String aName;

    /**
     * List of child EnclosureCollections
     */
    private List<EnclosureCollection> aEnclosures;

    /**
     * Construction to create a new CompositeEnclosureCollection.
     * @param pName Name ot the composite area/section
     */
    public CompositeEnclosureCollection(String pName) {
        this.aName = pName;
        this.aEnclosures = new ArrayList<>();
    }

    /**
     * Adds a child EnclosureCollection (enclosure or composite).
     * @param pCollection The EnclosureCollection to add
     */
    public void addCollection(EnclosureCollection pCollection) {
        this.aEnclosures.add(pCollection);
    }

    /**
     * Removes a child EnclosureCollection.
     * @param pCollection The EnclosureCollection to remove
     */
    public void removeCollection(EnclosureCollection pCollection) {
        this.aEnclosures.remove(pCollection);
    }

    /**
     * Get the list of child collections of this composite.
     * @return List of child EnclosureCollection objects
     */
    public List<EnclosureCollection> getChildren() {
        return this.aEnclosures;
    }

    /**
     * Recursively collect all leaf Enclosures within this composite.
     * @return List of leaf Enclosure objects
     */
    public List<EnclosureCollection> getEnclosures() {
        List<EnclosureCollection> enclosures = new ArrayList<>();
        for (EnclosureCollection child : this.aEnclosures) {
            //enclosures.addAll(child.getEnclosures());
        }
        return enclosures;
    }

    /**
     *
     * @return Name of this composite area
     */
    @Override
    public String getName() {
        return this.aName;
    }

    /**
     *
     * @return Name of this composite area for display purpose
     */
    @Override
    public String toString() {
        return this.aName;
    }
}

