package ua.kiev.naiv.drinkit.cocktail.web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeEntity;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils;

import java.io.IOException;

/**
 * @author pkolmykov
 */
public class TransformTests {

    @Test
    public void testRecipeInput() throws IOException {
        String json = "{\"cocktailTypeId\":1,\"description\":\"desc\",\"name\":\"Test2\",\"options\":[1,2],\"cocktailIngredients\":[[1,50],[2,60]]}";
        ObjectMapper objectMapper = new ObjectMapper();
        Assert.assertEquals(json, objectMapper.writeValueAsString(creteMockRecipeInput()));
        Assert.assertEquals(creteMockRecipeInput(), objectMapper.readValue(json, Recipe.class));
        RecipeEntity recipeEntity = TransformUtils.transform(creteMockRecipeInput());
        System.out.println(recipeEntity);
    }

    public Recipe creteMockRecipeInput() {
        Recipe recipe = new Recipe();
        recipe.setCocktailTypeId(1);
        recipe.setDescription("desc");
        recipe.setName("Test2");
        recipe.setOptions(new int[]{1, 2});
        recipe.setCocktailIngredients(new int[][]{{1, 50}, {2, 60}});
        return recipe;
    }
}
