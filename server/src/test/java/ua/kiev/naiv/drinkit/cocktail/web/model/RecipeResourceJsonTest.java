package ua.kiev.naiv.drinkit.cocktail.web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ua.kiev.naiv.drinkit.MockObjectsGenerator;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RecipeResourceJsonTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void readWriteJson() throws IOException {
        RecipeResource mockRecipeResource = MockObjectsGenerator.creteMockRecipe();
//        mockRecipe.setId(1);
        String json = objectMapper.writeValueAsString(mockRecipeResource);
        System.out.println(json);
        RecipeResource parsedRecipeResource = objectMapper.readValue(json, RecipeResource.class);
        assertEquals(mockRecipeResource, parsedRecipeResource);
    }

    @Test
    public void readJson() throws IOException {
        objectMapper.readValue(getClass().getResource("recipe.json"), RecipeResource.class);
    }

}