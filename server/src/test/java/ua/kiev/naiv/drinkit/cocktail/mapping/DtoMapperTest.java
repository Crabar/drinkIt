package ua.kiev.naiv.drinkit.cocktail.mapping;

import org.junit.Test;
import ua.kiev.naiv.drinkit.cocktail.AbstractBaseTest;
import ua.kiev.naiv.drinkit.cocktail.persistence.entity.Recipe;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

public class DtoMapperTest extends AbstractBaseTest {
    @Resource
    DtoMapper dtoMapper;

    @Test
    public void testRecipeConverting() {
        Recipe newRecipe = createNewRecipe();
        RecipeDto recipeDto = dtoMapper.map(newRecipe, RecipeDto.class);
        Recipe recipe = dtoMapper.map(recipeDto, Recipe.class);
        assertEquals(newRecipe, recipe);
    }

}