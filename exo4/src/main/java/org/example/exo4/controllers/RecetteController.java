package org.example.exo4.controllers;

import jakarta.validation.Valid;
import org.example.exo4.entities.Recette;
import org.example.exo4.services.ICategoryService;
import org.example.exo4.services.IRecetteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/recette")
public class RecetteController {

    private IRecetteService recetteService;
    private ICategoryService categoryService;

    public RecetteController(final IRecetteService studentService, ICategoryService icategoryService) {
        this.recetteService = studentService;
        this.categoryService = icategoryService;
    }

    @GetMapping("/list")
    public String listPage(Model model){
        model.addAttribute("allRecette", recetteService.getAllRecette());
        model.addAttribute("isFilter", false);
        return "listRecette";
    }

    @GetMapping("/detail")
    public String detailPage(Model model, @RequestParam(value="uuid") UUID uuid){
        model.addAttribute("recette", recetteService.getRecetteByUUID(uuid));
        return "detailRecette";
    }

    @GetMapping("/add")
    public String addRecette(Model model){
        Recette recette = new Recette();
        model.addAttribute("recette", recette);
        model.addAttribute("categories", categoryService.getAllCategorie());
        return "addRecette";
    }

    @PostMapping("/add")
    public String submitRecette(@Valid @ModelAttribute("recette") Recette recette, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "addRecette";

        if(recette.getId() != null){
            recette.setCategorie(categoryService.getCategorieByUUID(UUID.fromString(recette.getCategorieID())));
            recetteService.updateRecette(recette);
        }
        else
            recetteService.addRecette(recette.getNom(), recette.getIngredients(), recette.getInstructions(), categoryService.getCategorieByUUID(UUID.fromString(recette.getCategorieID())));

        return "redirect:/recette/list";
    }

    @GetMapping("/update")
    public String updateRecette(Model model, @RequestParam(value="uuid") UUID uuid){
        model.addAttribute("recette", recetteService.getRecetteByUUID(uuid));
        model.addAttribute("categories", categoryService.getAllCategorie());
        return "addRecette";
    }

    @GetMapping("/delete")
    public String searchPage(@RequestParam(value="uuid") UUID uuid){
        recetteService.deleteRecette(uuid);
        return "redirect:/recette/list";
    }
}
