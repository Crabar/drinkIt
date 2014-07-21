package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.stereotype.Service;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeComparatorByCriteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipeRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.SearchSpecification;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils.transform;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Resource
    RecipeRepository recipeRepository;

    @Override
    public int save(Recipe recipe) {
        return recipeRepository.saveAndFlush(transform(recipe)).getId();
    }

    @Override
    public void delete(int id) {
        recipeRepository.delete(id);
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll().stream()
                .map(TransformUtils::transform)
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> findByCriteria(Criteria criteria) {
        return recipeRepository.findAll(SearchSpecification.byCriteria(criteria)).stream()
                .sorted(new RecipeComparatorByCriteria(criteria))
                .map(TransformUtils::transform)
                .collect(Collectors.toList());
    }

    @Override
    public Recipe getRecipeById(int id) {
        return transform(recipeRepository.findOne(id));
    }


}
