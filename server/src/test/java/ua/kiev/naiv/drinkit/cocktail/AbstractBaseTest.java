package ua.kiev.naiv.drinkit.cocktail;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.naiv.drinkit.cocktail.persistence.entity.*;
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 09.11.2014
 * Time: 16:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional()
public class AbstractBaseTest {
    protected Ingredient firstIngredient = new Ingredient();
    protected Ingredient secondIngredient = new Ingredient();
    @Autowired
    private IngredientService ingredientService;

    @Before
    public void init() {
        firstIngredient.setDescription("firstIngredient");
        firstIngredient.setName("First");
        firstIngredient.setVol(30);
        ingredientService.create(firstIngredient);

        secondIngredient.setDescription("secondIngredient");
        secondIngredient.setName("Second");
        secondIngredient.setVol(40);
        ingredientService.create(secondIngredient);
    }

    protected RecipeDto createNewRecipeDto() {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setCocktailTypeId(1);
        recipeDto.setDescription("desc");
        recipeDto.setName("Recipe for integration tests");
        recipeDto.setOptions(new int[]{1, 2});
        recipeDto.setCocktailIngredients(new Integer[][]{{firstIngredient.getId(), 50}, {secondIngredient.getId(), 60}});
        return recipeDto;
    }

    protected Recipe createNewRecipe() {
        Recipe recipe = new Recipe();
        recipe.setCocktailType(new CocktailType(1));
        recipe.setDescription("desc");
        recipe.setName("Recipe for integration tests");
        recipe.setOptions(new ArrayList<Option>() {{
            add(new Option(1));
        }});
        recipe.setIngredientsWithQuantities(new ArrayList<IngredientWithQuantity>() {{
            IngredientWithQuantity ingredientWithQuantity = new IngredientWithQuantity();
            ingredientWithQuantity.setQuantity(50);
            ingredientWithQuantity.setIngredient(firstIngredient);
            ingredientWithQuantity.setRecipe(recipe);
            add(ingredientWithQuantity);
        }});
//        recipe.setIngredientsWithQuantities(new Integer[][]{{firstIngredient.getId(), 50}, {secondIngredient.getId(), 60}});
        return recipe;
    }

    protected Ingredient createNewIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("new ingredient");
        ingredient.setName("new");
        ingredient.setVol(44);
        return ingredient;
    }
}
