package org.example.exo4.services;

import org.example.exo4.entities.Categorie;
import org.example.exo4.entities.Recette;

import java.util.List;
import java.util.UUID;

public interface IRecetteService {

    List<Recette> getAllRecette();
    Recette getRecetteByUUID(UUID uuid) ;
    Recette getRecetteByName(String nom) ;
    void addRecette(String name, String ingredients, String instructions, Categorie categorie) ;
    void updateRecette(Recette recette) ;
    void deleteRecette(UUID id) ;
}
