package ua.kiev.naiv.drinkit.cocktail.common;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Option;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeEntity;
import ua.kiev.naiv.drinkit.cocktail.web.controller.RecipeController;
import ua.kiev.naiv.drinkit.cocktail.web.model.RecipeResource;

@Component
public class RecipeResourceAssembler extends ResourceAssemblerSupport<RecipeEntity, RecipeResource> {
    public RecipeResourceAssembler() {
        super(RecipeController.class, RecipeResource.class);
    }

    @Override
    public RecipeResource toResource(RecipeEntity recipeEntity) {
        RecipeResource recipeResource = createResourceWithId(recipeEntity.getId(), recipeEntity);
//        recipe.setImage(recipeEntity.getImage());
//        recipe.setThumbnail(recipeEntity.getThumbnail());
        recipeResource.setCocktailTypeId(recipeEntity.getCocktailType().getId());
        recipeResource.setDescription(recipeEntity.getDescription());
        recipeResource.setName(recipeEntity.getName());
        recipeResource.setCocktailIngredients(recipeEntity.getIngredientsWithQuantities().stream()
                .<Integer[]>map(val -> new Integer[]{val.getIngredient().getId(), val.getQuantity()})
                .toArray(Integer[][]::new));
        recipeResource.setOptions(recipeEntity.getOptions().stream()
                .mapToInt(Option::getId)
                .toArray());
        return recipeResource;
    }
}
