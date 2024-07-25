package org.example.exo5.repositories;

import org.example.exo5.entities.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
    List<Furniture> findAllByOrderByNameAsc();
}
