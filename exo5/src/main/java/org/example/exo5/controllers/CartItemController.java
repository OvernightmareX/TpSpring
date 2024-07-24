package org.example.exo5.controllers;

import org.example.exo5.entities.CartItem;
import org.example.exo5.repositories.CartItemRepository;
import org.example.exo5.repositories.FurnitureRepository;
import org.example.exo5.services.CartService;
import org.example.exo5.services.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartItemController {
    private final CartService cartService;
    private final FurnitureService furnitureService;

    public CartItemController(CartService cartService, FurnitureService furnitureService) {
        this.cartService = cartService;
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public String showCartItem(Model model) {
        model.addAttribute("cartItems", cartService.getAllCartItems());
        return "cart";
    }

    @GetMapping("/add")
    public String addCartItem(@RequestParam("id") long id, Model model) {
        CartItem cartItem = CartItem.builder().quantity(1).furnitureId(furnitureService.getFurnitureById(id)).build();
        cartService.addCartItem(cartItem);
        return "redirect:/furniture";
    }

    @GetMapping
    public String deleteCartItem(@RequestParam("id") long id, Model model) {
        cartService.removeFromCart();
    }
}
