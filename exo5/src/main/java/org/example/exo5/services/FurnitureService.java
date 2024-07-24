package org.example.exo5.services;

import org.example.exo5.entities.Furniture;

import java.util.List;

public interface FurnitureService {
    List<Furniture> getAllFurniture();
    Furniture getFurnitureById(long id);
    void saveFurniture(Furniture furniture);
    void deleteFurniture(long id);
}
