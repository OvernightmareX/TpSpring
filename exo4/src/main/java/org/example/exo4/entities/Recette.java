package org.example.exo4.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recette {
    private UUID id;
    @NotBlank
    private String nom;
    @NotBlank
    private String ingredients;
    @NotBlank
    private String instructions;
    private Categorie categorie;
    @NotNull
    private String categorieID;
}
