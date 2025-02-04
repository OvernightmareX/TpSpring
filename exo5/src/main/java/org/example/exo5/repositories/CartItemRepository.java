package org.example.exo5.repositories;

import org.example.exo5.entities.CartItem;
import org.example.exo5.entities.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findCartItemByFurnitureId(Furniture furniture);
}
