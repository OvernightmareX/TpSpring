package org.example.exo4.services;

import org.example.exo4.entities.Categorie;
import org.example.exo4.entities.Recette;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecetteService implements IRecetteService {
    private final List<Recette> recetteList;

    public RecetteService() {
        recetteList = new ArrayList<>();
    }

    public List<Recette> getAllRecette(){
        return recetteList;
    }

    public Recette getRecetteByUUID(UUID uuid) {
        return recetteList.stream().filter(recette -> recette.getId().equals(uuid)).findFirst().orElse(null);
    }

    public Recette getRecetteByName(String name) {
        return recetteList.stream().filter(recette -> recette.getNom().toUpperCase().startsWith(name.toUpperCase())).findFirst().orElse(null);
    }

    public void addRecette(String nom, String ingredients, String instructions, Categorie categorie)  {
        Recette recette = Recette.builder()
                .id(UUID.randomUUID())
                .nom(nom)
                .ingredients(ingredients)
                .instructions(instructions)
                .categorie(categorie)
                .build();

        recetteList.add(recette);
    }

    public void updateRecette(Recette recette) {
        Recette studentToUpdate = getRecetteByUUID(recette.getId());
        studentToUpdate.setNom(recette.getNom());
        studentToUpdate.setIngredients(recette.getIngredients());
        studentToUpdate.setInstructions(recette.getInstructions());
        studentToUpdate.setCategorie(recette.getCategorie());
    }

    public void deleteRecette(UUID id) {
        recetteList.removeIf(recette -> recette.getId().equals(id));
    }

}
