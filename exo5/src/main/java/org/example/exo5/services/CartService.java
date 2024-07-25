package org.example.exo5.services;

import org.example.exo5.entities.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getAllCartItems();
    CartItem getCartItemByID(long id);
    void addCartItem(long furnitureId, int quantity);
    void removeFromCart(CartItem cartItem);
    void clearCart();
}
