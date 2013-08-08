package ua.kiev.naiv.drinkit.cocktail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;

/**
 * @author pkolmykov
 */
@Controller
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    CocktailService cocktailService;

    @RequestMapping("/test.html")
    public ModelAndView test() {
        return new ModelAndView("test");
    }

    @RequestMapping("/list.html")
    public ModelAndView list() {
        return new ModelAndView("list", "list", cocktailService.findAll());
    }

    @RequestMapping("/addNew.html")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("recipe", new Recipe());
        modelMap.addAttribute("cocktailTypes", cocktailService.getCocktailTypes());
        return "recipeEditForm";
    }
}