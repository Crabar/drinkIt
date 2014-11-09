package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.naiv.drinkit.cocktail.common.aspect.EnableStats;
import ua.kiev.naiv.drinkit.cocktail.mapping.DtoMapper;
import ua.kiev.naiv.drinkit.cocktail.persistence.entity.Recipe;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipeRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.SearchSpecification;
import ua.kiev.naiv.drinkit.cocktail.service.FileStoreService;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Resource
    FileStoreService fileStoreService;

    @Resource
    private RecipeRepository recipeRepository;

    @Resource
    private DtoMapper dtoMapper;

    @Override
    public void save(RecipeDto recipeDto) {
        recipeRepository.saveAndFlush(dtoMapper.map(recipeDto, Recipe.class));
    }

    @Override
    public void delete(int id) {
        recipeRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findAll() {
        return dtoMapper.mapAsList(recipeRepository.findAll(), RecipeDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findByCriteria(Criteria criteria) {
        return dtoMapper.mapAsList(recipeRepository.findAll(SearchSpecification.byCriteria(criteria)), RecipeDto.class);
    }

    @Override
    @EnableStats
    @Transactional(readOnly = true)
    public RecipeDto getRecipeById(int id) {
        return dtoMapper.map(recipeRepository.findOne(id), RecipeDto.class);
    }


    @Override
    public List<RecipeDto> findByRecipeNameContaining(String namePart) {
        return dtoMapper.mapAsList(recipeRepository.findByNameContaining(namePart), RecipeDto.class);
    }


    @Override
    @Transactional
    public void saveMedia(int recipeId, byte[] image, byte[] thumbnail) throws IOException {
        Recipe recipe = recipeRepository.findOne(recipeId);
        recipe.setImageFileName(fileStoreService.save(recipeId, image, "image"));
        recipe.setThumbnailFileName(fileStoreService.save(recipeId, thumbnail, "thumbnail"));
        recipeRepository.saveAndFlush(recipe);
    }
}
