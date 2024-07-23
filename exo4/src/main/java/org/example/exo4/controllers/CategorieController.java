package org.example.exo4.controllers;

import org.example.exo4.entities.Categorie;
import org.example.exo4.entities.Recette;
import org.example.exo4.services.ICategoryService;
import org.example.exo4.services.IRecetteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/categorie")
public class CategorieController {

    private ICategoryService categoryService;

    public CategorieController(ICategoryService icategoryService) {
        this.categoryService = icategoryService;
    }

    @GetMapping("/list")
    public String listPage(Model model){
        model.addAttribute("allCategorie", categoryService.getAllCategorie());
        model.addAttribute("isFilter", false);
        return "listCategorie";
    }

    @GetMapping("/detail")
    public String detailPage(Model model, @RequestParam(value="uuid") UUID uuid){
        model.addAttribute("categorie", categoryService.getCategorieByUUID(uuid));
        return "detailCategorie";
    }

    @GetMapping("/add")
    public String addCategorie(Model model){
        Categorie categorie = new Categorie();
        model.addAttribute("categorie", categorie);
        return "addCategorie";
    }

    @PostMapping("/add")
    public String submitCategorie(@ModelAttribute("categorie") Categorie categorie){
        if(categorie.getId() != null){
            categoryService.updateCategorie(categorie);
        }
        else
            categoryService.addCategorie(categorie.getNom(), categorie.getDescription());

        return "redirect:/categorie/list";
    }

    @GetMapping("/update")
    public String updateRecette(Model model, @RequestParam(value="uuid") UUID uuid){
        model.addAttribute("categorie", categoryService.getCategorieByUUID(uuid));
        return "addCategorie";
    }

    @GetMapping("/delete")
    public String searchPage(@RequestParam(value="uuid") UUID uuid){
        categoryService.deleteCategorie(uuid);
        return "redirect:/categorie/list";
    }
}
