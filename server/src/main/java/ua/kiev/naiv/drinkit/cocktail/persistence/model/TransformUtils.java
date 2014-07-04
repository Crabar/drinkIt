package ua.kiev.naiv.drinkit.cocktail.persistence.model;

import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author pkolmykov
 */
public class TransformUtils {


    public static Recipe transform(RecipeEntity recipeEntity) {
        return null;
    }

    public static RecipeEntity transform(Recipe recipe) {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setName(recipe.getName());
        recipeEntity.setDescription(recipe.getDescription());
        recipeEntity.setOptions(Arrays.stream(recipe.getOptions()).<Option>mapToObj(Option::new).collect(Collectors.toSet()));
        recipeEntity.setCocktailType(new CocktailType(recipe.getCocktailTypeId()));
        recipeEntity.setIngredientsWithQuantities(Arrays.stream(recipe.getCocktailIngredients()).<IngredientWithQuantity>map(val -> {
            IngredientWithQuantity ingredientWithQuantity = new IngredientWithQuantity();
            ingredientWithQuantity.setQuantity(val[1]);
            ingredientWithQuantity.setRecipe(recipeEntity);
            ingredientWithQuantity.setIngredient(new Ingredient(val[0]));
            return ingredientWithQuantity;
        }).collect(Collectors.toSet()));
        recipeEntity.setImage(recipe.getImage());
        return recipeEntity;
    }

}
