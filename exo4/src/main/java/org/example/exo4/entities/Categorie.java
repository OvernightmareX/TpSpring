package org.example.exo4.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {
    private UUID id;
    @NotBlank
    private String nom;
    @NotBlank
    private String description;
}
