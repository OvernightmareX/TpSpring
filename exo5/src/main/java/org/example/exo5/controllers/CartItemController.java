package org.example.exo5.controllers;

import org.example.exo5.entities.CartItem;
import org.example.exo5.entities.Furniture;
import org.example.exo5.repositories.CartItemRepository;
import org.example.exo5.repositories.FurnitureRepository;
import org.example.exo5.services.CartService;
import org.example.exo5.services.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartItemController {
    private final CartService cartService;

    public CartItemController(CartService cartService, FurnitureService furnitureService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String showCartItem(Model model) {
        model.addAttribute("cartItems", cartService.getAllCartItems());
        return "cart";
    }

    @PostMapping("/add")
    public String addCartItem(@RequestParam("id") long id, @RequestParam("quantity") int quantity) {
        cartService.addCartItem(id, quantity);
        return "redirect:/furniture";
    }

    @GetMapping("/delete")
    public String deleteCartItem(@RequestParam("id") long id) {
        cartService.removeFromCart(cartService.getCartItemByID(id));
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCartItem() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}
