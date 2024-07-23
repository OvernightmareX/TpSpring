package org.example.exo4.services;

import org.example.exo4.entities.Categorie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategorieService implements ICategoryService {
    List<Categorie> categories;

    public CategorieService() {
        categories = new ArrayList<Categorie>();
        categories.add(Categorie.builder()
                        .id(UUID.randomUUID())
                        .nom("Plat principal")
                        .description("C'est un plat....principal ?")
                        .build());

        categories.add(Categorie.builder()
                .id(UUID.randomUUID())
                .nom("Dessert")
                .description("C'est un repas après le repas !")
                .build());
    }

    @Override
    public List<Categorie> getAllCategorie() {
        return categories;
    }

    @Override
    public Categorie getCategorieByUUID(UUID uuid) {
        return categories.stream().filter(c -> c.getId().equals(uuid)).findFirst().orElse(null);
    }

    @Override
    public Categorie getCategorieByName(String nom) {
        return categories.stream().filter(c -> c.getNom().equals(nom)).findFirst().orElse(null);
    }

    @Override
    public void addCategorie(String name, String description) {
        Categorie categorie = Categorie.builder()
                .id(UUID.randomUUID())
                .nom(name)
                .description(description)
                .build();

        categories.add(categorie);
    }

    @Override
    public void updateCategorie(Categorie categorie) {
        Categorie categorieToUpdate = getCategorieByUUID(categorie.getId());
        categorieToUpdate.setNom(categorie.getNom());
        categorieToUpdate.setDescription(categorie.getDescription());
    }

    @Override
    public void deleteCategorie(UUID id) {
        categories.removeIf(c -> c.getId().equals(id));
    }
}
