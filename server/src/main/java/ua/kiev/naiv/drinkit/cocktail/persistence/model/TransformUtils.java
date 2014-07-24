package ua.kiev.naiv.drinkit.cocktail.persistence.model;

import ua.kiev.naiv.drinkit.cocktail.web.model.RecipeResource;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TransformUtils {


//    public static Recipe transform(RecipeEntity recipeEntity) {
//        if(recipeEntity == null){
//            return null;
//        }
//        Recipe recipe = new Recipe();
////        recipe.setId(recipeEntity.getId());
//        recipe.setImage(recipeEntity.getImage());
//        recipe.setThumbnail(recipeEntity.getThumbnail());
//        recipe.setCocktailTypeId(recipeEntity.getCocktailType().getId());
//        recipe.setDescription(recipeEntity.getDescription());
//        recipe.setName(recipeEntity.getName());
//        recipe.setCocktailIngredients(recipeEntity.getIngredientsWithQuantities().stream()
//                .<Integer[]>map(val -> new Integer[]{val.getIngredient().getId(), val.getQuantity()})
//                .toArray(Integer[][]::new));
//        recipe.setOptions(recipeEntity.getOptions().stream()
//                .mapToInt(Option::getId)
//                .toArray());
//        return recipe;
//    }

    public static RecipeEntity transform(RecipeResource recipeResource) {
        RecipeEntity recipeEntity = new RecipeEntity();
//        recipeEntity.setId(recipe.getId());
        recipeEntity.setName(recipeResource.getName());
        recipeEntity.setDescription(recipeResource.getDescription());
        recipeEntity.setOptions(Arrays.stream(recipeResource.getOptions()).<Option>mapToObj(Option::new).collect(Collectors.toList()));
        recipeEntity.setCocktailType(new CocktailType(recipeResource.getCocktailTypeId()));
        recipeEntity.setIngredientsWithQuantities(Arrays.stream(recipeResource.getCocktailIngredients()).<IngredientWithQuantity>map(val -> {
            IngredientWithQuantity ingredientWithQuantity = new IngredientWithQuantity();
            ingredientWithQuantity.setQuantity(val[1]);
            ingredientWithQuantity.setRecipeEntity(recipeEntity);
            ingredientWithQuantity.setIngredient(new Ingredient(val[0]));
            return ingredientWithQuantity;
        }).collect(Collectors.toList()));
        recipeEntity.setImage(recipeResource.getImage());
        recipeEntity.setThumbnail(recipeResource.getThumbnail());
        return recipeEntity;
    }

}
