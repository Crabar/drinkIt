package ua.kiev.naiv.drinkit.cocktail.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;

import java.io.IOException;
import java.util.List;

public interface RecipeService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void save(RecipeDto recipeDto);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(int id);

    List<RecipeDto> findAll();

    List<RecipeDto> findByCriteria(Criteria criteria);

    List<RecipeDto> findByRecipeNameContaining(String namePart);

    RecipeDto getRecipeById(int id);

    void saveMedia(int recipeId, byte[] image, byte[] thumbnail) throws IOException;
}
