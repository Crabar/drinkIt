package ua.kiev.naiv.drinkit.cocktail.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import ua.kiev.naiv.drinkit.cocktail.common.DrinkitUtils;
import ua.kiev.naiv.drinkit.cocktail.mapping.DtoMapper;
import ua.kiev.naiv.drinkit.cocktail.persistence.entity.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;
import ua.kiev.naiv.drinkit.cocktail.web.dto.IngredientDto;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("ingredients")
public class IngredientsController {

    @Autowired
    IngredientService ingredientService;

    @Resource
    DtoMapper dtoMapper;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<IngredientDto> getIngredients() {
        return dtoMapper.mapAsList(ingredientService.getIngredients(), IngredientDto.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewIngredient(@RequestBody IngredientDto ingredientDto) {
        Assert.isNull(ingredientDto.getId());
        DrinkitUtils.logOperation("Creating ingredient", ingredientDto);
        ingredientService.create(dtoMapper.map(ingredientDto, Ingredient.class));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Updated")
    public void editIngredient(@RequestBody IngredientDto ingredientDto, @PathVariable int id) {
        Assert.isTrue(id == ingredientDto.getId(), "id from uri and id from json should be identical");
        DrinkitUtils.logOperation("Updating ingredient", ingredientDto);
        ingredientService.update(dtoMapper.map(ingredientDto, Ingredient.class));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Deleted")
    public void delete(@PathVariable int id) {
        DrinkitUtils.logOperation("Deleting ingredient", id);
        ingredientService.delete(id);
    }

}
