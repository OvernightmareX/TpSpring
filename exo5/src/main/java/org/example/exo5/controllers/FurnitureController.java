package org.example.exo5.controllers;

import org.example.exo5.entities.Furniture;
import org.example.exo5.services.FurnitureServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/furniture")
public class FurnitureController {
    private final FurnitureServiceImpl furnitureService;

    public FurnitureController(FurnitureServiceImpl furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("furnitures", furnitureService.getAllFurniture());
        return "listFurniture";
    }

    @GetMapping("/add")
    public String addFurniture(Model model) {
        model.addAttribute("furniture", new Furniture());
        return "addFurniture";
    }

    @PostMapping("/add")
    public String addFurniture(@ModelAttribute("furniture") Furniture furniture) {
        furnitureService.saveFurniture(furniture);
        return "redirect:/furniture";
    }

    @GetMapping("/update")
    public String updateFurniture(@RequestParam(name = "id") long id, Model model) {
        model.addAttribute("furniture", furnitureService.getFurnitureById(id));
        return "addFurniture";
    }

    @GetMapping("/delete")
    public String deleteFurniture(@RequestParam(name = "id") long id){
        furnitureService.deleteFurniture(id);
        return "redirect:/furniture";
    }
}
