package org.example.exo4.services;

import org.example.exo4.entities.Categorie;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {

    List<Categorie> getAllCategorie();
    Categorie getCategorieByUUID(UUID uuid) ;
    Categorie getCategorieByName(String nom) ;
    void addCategorie(String name, String description) ;
    void updateCategorie(Categorie categorie) ;
    void deleteCategorie(UUID id) ;
}
