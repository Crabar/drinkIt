package ua.kiev.naiv.drinkit.cocktail.persistence.model;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;

@SuppressWarnings("unused")
@Embeddable
public class CocktailIngredientId implements Serializable {

    private RecipeEntity recipeEntity;
    private Ingredient ingredient;

    @ManyToOne()
    public RecipeEntity getRecipeEntity() {
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
    }

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)//todo figure out
    @ManyToOne(fetch = FetchType.LAZY)
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Transient
    private int getId() {
        return ingredient.getId();
    }

    @Transient
    private void setId(int id) {
        ingredient.setId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CocktailIngredientId that = (CocktailIngredientId) o;

        if (!ingredient.equals(that.ingredient)) return false;
        if (!recipeEntity.equals(that.recipeEntity)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recipeEntity.hashCode();
        result = 31 * result + ingredient.hashCode();
        return result;
    }
}
