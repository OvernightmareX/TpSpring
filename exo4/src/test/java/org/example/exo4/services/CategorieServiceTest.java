package org.example.exo4.services;

import org.example.exo4.entities.Categorie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategorieServiceTest {

    private ICategoryService iCategoryService;
    private List<Categorie> categories;

    @Test
    public void testGetAllCategories_WhenListCategoryIsEmpty_ShouldReturnEmptyList() throws Exception {
        iCategoryService = new CategorieService(new ArrayList<>());
        List<Categorie> categorieList = iCategoryService.getAllCategorie();

        Assertions.assertTrue(categorieList.isEmpty());
    }

    @Test
    public void testGetAllCategories_WhenListCategoryIsNotEmpty_ShouldReturnListOfCategories() throws Exception {
        categories = new ArrayList<>();
        categories.add(Categorie.builder()
                .id(UUID.randomUUID())
                .nom("Plat principal")
                .description("C'est un plat....principal ?")
                .build());

        iCategoryService = new CategorieService(categories);
        List<Categorie> categorieList = iCategoryService.getAllCategorie();

        Assertions.assertEquals(1,categorieList.size());
        Assertions.assertEquals(categories.get(0).getId(),categorieList.get(0).getId());
        Assertions.assertEquals(categories.get(0).getNom(),categorieList.get(0).getNom());
        Assertions.assertEquals(categories.get(0).getDescription(),categorieList.get(0).getDescription());
    }

    @Test
    public void testGetCategorieById_WhenIdIsNull_ShouldReturnNullException() throws Exception {
        Assertions.assertThrows(NullPointerException.class, () -> iCategoryService.getCategorieByUUID(null));
    }

    @Test
    public void testGetCategorieByUUID_WhenIdIsNotNull_ShouldReturnCategorie() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String nom = "Plat principal";
        String description = "C'est un plat....principal ?";

        categories = new ArrayList<>();
        categories.add(Categorie.builder()
                .id(UUID.fromString(uuid))
                .nom(nom)
                .description(description)
                .build());

        iCategoryService = new CategorieService(categories);

        Categorie category = iCategoryService.getCategorieByUUID(UUID.fromString(uuid));

        // Assert
        Assertions.assertNotNull(category);
        Assertions.assertEquals(UUID.fromString(uuid), category.getId());
        Assertions.assertEquals(nom, category.getNom());
        Assertions.assertEquals(description, category.getDescription());
    }

    @Test
    public void testGetCategorieByUUID_WhenIdNotExists_ShouldReturnNull() throws Exception {
        categories = new ArrayList<>();
        categories.add(Categorie.builder()
                .id(UUID.randomUUID())
                .nom("Plat principal")
                .description("C'est un plat....principal ?")
                .build());

        iCategoryService = new CategorieService(categories);

        Categorie category = iCategoryService.getCategorieByUUID(UUID.randomUUID());

        Assertions.assertNull(category);
    }

    @Test
    public void testGetCategorieByName_WhenNameIsNull_ShouldReturnNull(){
        iCategoryService = new CategorieService(new ArrayList<>());
        Categorie category = iCategoryService.getCategorieByName(null);
        Assertions.assertNull(category);
    }

    @Test
    public void testGetCategorieByName_WhenNameIsEmpty_ShouldReturnNull(){
        iCategoryService = new CategorieService(new ArrayList<>());
        Categorie category = iCategoryService.getCategorieByName("");
        Assertions.assertNull(category);
    }

    @Test
    public void testGetCategorieByName_WhenNameIsValid_ShouldReturnCategorie(){
        String name = "Plat principal";
        String description = "C'est un plat....principal ?";

        categories = new ArrayList<>();
        categories.add(Categorie.builder()
                .id(UUID.randomUUID())
                .nom(name)
                .description(description)
                .build());

        iCategoryService = new CategorieService(categories);
        Categorie category = iCategoryService.getCategorieByName(name);
        Assertions.assertNotNull(category);
        Assertions.assertEquals(name, category.getNom());
        Assertions.assertEquals(description, category.getDescription());
    }

    @Test
    public void testGetCategorieByName_WhenNameIsInvalid_ShouldReturnNull(){
        categories = new ArrayList<>();
        categories.add(Categorie.builder()
                .id(UUID.randomUUID())
                .nom("Plat principal")
                .description("C'est un plat....principal ?")
                .build());

        iCategoryService = new CategorieService(categories);
        Categorie category = iCategoryService.getCategorieByName("test");
        Assertions.assertNull(category);
    }

    @Test
    public void testAddCategorie_WhenNameAndDescriptionIsNull_ShouldReturnNullValues() {
        iCategoryService = new CategorieService(new ArrayList<>());
        iCategoryService.addCategorie(null, null);
        List<Categorie> categories = iCategoryService.getAllCategorie();

        Assertions.assertFalse(categories.isEmpty());
        Assertions.assertEquals(1, categories.size());
        Assertions.assertNull(categories.get(0).getNom());
        Assertions.assertNull(categories.get(0).getDescription());
    }

    @Test
    public void testAddCategorie_WhenNameAndDescriptionIsNotNull_ShouldReturnCategorie() {
        iCategoryService = new CategorieService(new ArrayList<>());
        String name = "Plat principal";
        String description = "C'est un plat....principal ?";

        iCategoryService.addCategorie(name, description);
        List<Categorie> categories = iCategoryService.getAllCategorie();

        Assertions.assertFalse(categories.isEmpty());
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals(name, categories.get(0).getNom());
        Assertions.assertEquals(description, categories.get(0).getDescription());
    }

    @Test
    public void testUpdateCategorie_WhenCategorieIsNull_ShouldReturnNullException() throws Exception {
        iCategoryService = new CategorieService(new ArrayList<>());
        Assertions.assertThrows(NullPointerException.class, () -> iCategoryService.updateCategorie(null));
    }

    @Test
    public void testUpdateCategorie_WhenCategorieIsNotNull_ShouldUpdateCategorie() {
        categories = new ArrayList<>();
        String expectedName = "Test name";
        String expectedDescription = "Test description";

        Categorie category = Categorie.builder()
                .id(UUID.randomUUID())
                .nom("Plat principal")
                .description("C'est un plat....principal ?")
                .build();

        categories.add(category);
        iCategoryService = new CategorieService(categories);
        category.setNom(expectedName);
        category.setDescription(expectedDescription);

        iCategoryService.updateCategorie(category);
        Categorie updatedCategory = iCategoryService.getCategorieByUUID(category.getId());

        Assertions.assertNotNull(updatedCategory);
        Assertions.assertEquals(expectedName, updatedCategory.getNom());
        Assertions.assertEquals(expectedDescription, updatedCategory.getDescription());
    }

}
