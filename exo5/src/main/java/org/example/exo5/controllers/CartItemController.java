package org.example.exo5.controllers;

import org.example.exo5.services.CartService;
import org.example.exo5.services.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        if(quantity != 0)
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
