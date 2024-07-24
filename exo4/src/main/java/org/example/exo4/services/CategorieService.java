package org.example.exo4.services;

import org.example.exo4.entities.Categorie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategorieService implements ICategoryService {
    private List<Categorie> categories;

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

    public CategorieService(List<Categorie> categories) {
        this.categories = categories;
    }

    @Override
    public List<Categorie> getAllCategorie() {
        return categories;
    }

    @Override
    public Categorie getCategorieByUUID(UUID uuid) {
        if(uuid == null)
            throw new NullPointerException("Null UUID");

        return categories.stream().filter(c -> c.getId().equals(uuid)).findFirst().orElse(null);
    }

    @Override
    public Categorie getCategorieByName(String nom) {
        if(nom == null || nom.isEmpty())
            return null;

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
        if(categorie == null)
            throw new NullPointerException("Null Categorie");

        Categorie categorieToUpdate = getCategorieByUUID(categorie.getId());
        categorieToUpdate.setNom(categorie.getNom());
        categorieToUpdate.setDescription(categorie.getDescription());
    }

    @Override
    public void deleteCategorie(UUID id) {
        categories.removeIf(c -> c.getId().equals(id));
    }
}
