package ua.kiev.naiv.drinkit.cocktail.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;

import java.util.List;

/**
 * @author pkolmykov
 */
@Controller
public class CocktailController {

    @Autowired
    private CocktailController cocktailService;

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    @ResponseBody
    public List<Ingredient> getIngredients() {
        return cocktailService.getIngredients();
    }
}
