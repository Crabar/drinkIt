package ua.kiev.naiv.drinkit.cocktail.mapping;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ua.kiev.naiv.drinkit.cocktail.persistence.entity.Recipe;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;

/**
 * @author pkolmykov
 */
public class DtoMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Recipe.class, RecipeDto.class)
                .fieldMap("imageFileName", "imageUrl").aToB().
        .byDefault()
                .register();
    }
}
