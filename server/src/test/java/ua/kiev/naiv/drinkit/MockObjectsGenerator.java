package ua.kiev.naiv.drinkit;

import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.web.model.RecipeResource;

import java.util.Arrays;
import java.util.HashSet;

public class MockObjectsGenerator {

    public static RecipeResource creteMockRecipe() {
        RecipeResource recipeResource = new RecipeResource();
        recipeResource.setCocktailTypeId(1);
        recipeResource.setDescription("desc");
        recipeResource.setName("Test2");
        recipeResource.setOptions(new int[]{1, 2});
        recipeResource.setCocktailIngredients(new Integer[][]{{1, 50}, {2, 60}});
        return recipeResource;
    }

    public static Criteria createMockCriteria() {
        return new Criteria(new HashSet<>(Arrays.asList(1)),
                new HashSet<>(Arrays.asList(1)),
                new HashSet<>(Arrays.asList(1)));
    }

    public static Ingredient createMockIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("name");
        ingredient.setDescription("desc");
        ingredient.setVol(30);
        return ingredient;
    }
}
