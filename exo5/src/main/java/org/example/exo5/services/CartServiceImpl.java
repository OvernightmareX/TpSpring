package org.example.exo5.services;

import org.example.exo5.entities.CartItem;
import org.example.exo5.entities.Furniture;
import org.example.exo5.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final FurnitureService furnitureService;

    CartServiceImpl(CartItemRepository cartItemRepository, FurnitureService furnitureService) {
        this.cartItemRepository = cartItemRepository;
        this.furnitureService = furnitureService;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemByID(long id) {
        return cartItemRepository.getReferenceById(id);
    }

    public void addCartItem(long furnitureID, int quantity) {
        Furniture furniture = furnitureService.getFurnitureById(furnitureID);
        CartItem cartItem = cartItemRepository.findCartItemByFurnitureId(furniture);

        furniture.setStock(furniture.getStock() - quantity);
        furniture.setCartItem(cartItem);
        furnitureService.saveFurniture(furniture);

        if(cartItem != null)
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        else
            cartItem = CartItem.builder().quantity(quantity).furnitureId(furniture).build();

        cartItemRepository.save(cartItem);
    }

    public void removeFromCart(CartItem cartItem) {
        Furniture furniture = cartItem.getFurnitureId();
        furniture.setStock(furniture.getStock() + cartItem.getQuantity());
        furnitureService.saveFurniture(furniture);

        cartItemRepository.delete(cartItem);
    }

    public void clearCart() {
        for (CartItem cartItem : getAllCartItems()) {
            removeFromCart(cartItem);
        }
    }
}
