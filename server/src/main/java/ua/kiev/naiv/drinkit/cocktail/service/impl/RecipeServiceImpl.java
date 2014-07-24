package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeEntity;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipeRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.SearchSpecification;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.RecipeResource;

import javax.annotation.Resource;

import static ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils.transform;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Resource
    RecipeRepository recipeRepository;

    @Override
    public int save(RecipeResource recipeResource) {
        return recipeRepository.saveAndFlush(transform(recipeResource)).getId();
    }

    @Override
    public void delete(int id) {
        recipeRepository.delete(id);
    }

//    @Override
//    public List<Recipe> findAll() {
//        return recipeRepository.findAll().stream()
//                .map(TransformUtils::transform)
//                .collect(Collectors.toList());
//    }

    @Override
    public Page<RecipeEntity> findByCriteria(Criteria criteria, Pageable pageable) {
        return recipeRepository.findAll(SearchSpecification.byCriteria(criteria), pageable);
    }

    @Override
    public RecipeEntity getRecipeById(int id) {
        return recipeRepository.findOne(id);
    }

    @Override
    public Page<RecipeEntity> findAll(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }


}
