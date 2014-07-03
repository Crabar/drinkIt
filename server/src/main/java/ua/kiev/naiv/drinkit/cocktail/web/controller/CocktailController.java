package ua.kiev.naiv.drinkit.cocktail.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 21.07.13
 * Time: 13:54
 */
@Controller
@RequestMapping(value = "cocktails")
public class CocktailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CocktailController.class);

	@Autowired
	CocktailService cocktailService;

	@RequestMapping(value = "/{cocktailId}", method = RequestMethod.GET)
	@ResponseBody
    @Transactional(readOnly = true)
	public Recipe getRecipeById(@PathVariable int cocktailId) {
		return cocktailService.getRecipeById(cocktailId);
	}

    @RequestMapping(method = RequestMethod.POST)
    public void createRecipe(@RequestBody Recipe recipe){
        cocktailService.create(recipe);
    }

	@RequestMapping(value = "/getIngredients", method = RequestMethod.GET)
	@ResponseBody
	public List<Ingredient> getIngredients() {
		return cocktailService.getIngredients();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
//	@JsonMixin(value = RecipeSearchResult.class, targetClass = RecipeEntity.class)
    @Transactional(readOnly = true)
	public List<Recipe> searchRecipes(@RequestParam(value = "criteria") String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		Criteria criteria;
		try {
			criteria = objectMapper.readValue(json, Criteria.class);
		} catch (IOException e) {
			LOGGER.error("Bad criteria", e);
			return null;
		}

		return cocktailService.findByCriteria(criteria);
	}

}
