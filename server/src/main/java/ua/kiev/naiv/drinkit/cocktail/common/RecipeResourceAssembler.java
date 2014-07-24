package ua.kiev.naiv.drinkit.cocktail.common;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Option;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeEntity;
import ua.kiev.naiv.drinkit.cocktail.web.controller.RecipeController;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

@Component
public class RecipeResourceAssembler extends ResourceAssemblerSupport<RecipeEntity, Recipe> {
    public RecipeResourceAssembler() {
        super(RecipeController.class, Recipe.class);
    }

    @Override
    public Recipe toResource(RecipeEntity recipeEntity) {
        Recipe recipe = createResourceWithId(recipeEntity.getId(), recipeEntity);
//        recipe.setImage(recipeEntity.getImage());
//        recipe.setThumbnail(recipeEntity.getThumbnail());
        recipe.setCocktailTypeId(recipeEntity.getCocktailType().getId());
        recipe.setDescription(recipeEntity.getDescription());
        recipe.setName(recipeEntity.getName());
        recipe.setCocktailIngredients(recipeEntity.getIngredientsWithQuantities().stream()
                .<Integer[]>map(val -> new Integer[]{val.getIngredient().getId(), val.getQuantity()})
                .toArray(Integer[][]::new));
        recipe.setOptions(recipeEntity.getOptions().stream()
                .mapToInt(Option::getId)
                .toArray());
        return recipe;
    }
}
