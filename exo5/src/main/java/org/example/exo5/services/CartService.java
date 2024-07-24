package org.example.exo5.services;

import org.example.exo5.entities.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getAllCartItems();
    void addCartItem(CartItem cartItem);
    void removeFromCart(CartItem cartItem);
    void clearCart();
}
