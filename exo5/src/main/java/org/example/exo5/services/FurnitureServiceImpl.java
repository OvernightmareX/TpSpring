package org.example.exo5.services;

import org.example.exo5.entities.Furniture;
import org.example.exo5.repositories.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureServiceImpl implements FurnitureService{
    private final FurnitureRepository furnitureRepository;

    public FurnitureServiceImpl(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public List<Furniture> getAllFurniture() {
        return furnitureRepository.findAll();
    }

    public Furniture getFurnitureById(long id) {
        return furnitureRepository.findById(id).orElse(null);
    }

    public void saveFurniture(Furniture furniture) {
        furnitureRepository.save(furniture);
    }

    public void deleteFurniture(long id) {
        furnitureRepository.delete(getFurnitureById(id));
    }
}
