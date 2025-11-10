package com.example.assign3zms.Model;

import java.util.List;

/**
 * Interface representing a collection of enclosures
 *
 * Author: Rohina
 */
public interface EnclosureCollection {

    /**
     * Get all the leaf Enclosure objects inside this collection.
     * @return List of Enclosure
     */
    List<Enclosure> getEnclosures();

    /**
     * Get the name of this collection or enclosure.
     * @return String name
     */
    String getName();
}
