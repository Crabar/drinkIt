package ua.kiev.naiv.drinkit.cocktail.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeEntity;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.web.model.RecipeResource;

public interface RecipeService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    int save(RecipeResource recipeResource);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(int id);

//    List<Recipe> findAll();

    Page<RecipeEntity> findByCriteria(Criteria criteria, Pageable pageable);

    RecipeEntity getRecipeById(int id);

    Page<RecipeEntity> findAll(Pageable pageable);
}
