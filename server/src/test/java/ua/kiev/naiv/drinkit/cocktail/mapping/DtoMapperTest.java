package ua.kiev.naiv.drinkit.cocktail.mapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kiev.naiv.drinkit.cocktail.AbstractBaseTest;
import ua.kiev.naiv.drinkit.cocktail.persistence.entity.Recipe;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipeRepository;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class DtoMapperTest extends AbstractBaseTest {
    @Resource
    DtoMapper dtoMapper;
    @Resource
    RecipeRepository recipeRepository;

    @Test
    public void testRecipeConverting() {
        Recipe newRecipe = recipeRepository.save(createNewRecipe());
        RecipeDto recipeDto = dtoMapper.map(newRecipe, RecipeDto.class);
        Recipe recipe = dtoMapper.map(recipeDto, Recipe.class);
        assertEquals(recipe, newRecipe);
    }

}