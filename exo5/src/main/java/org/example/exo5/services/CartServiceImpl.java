package org.example.exo5.services;

import org.example.exo5.entities.CartItem;
import org.example.exo5.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;

    CartServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public void addCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public void removeFromCart(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }
}
