package ua.kiev.naiv.drinkit.cocktail.persistence.entity;

import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class RecipeComparatorByCriteria implements Comparator<Recipe> {

    private final Criteria criteria;

    public RecipeComparatorByCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public int compare(Recipe recipe1, Recipe recipe2) {
        int result = getOverlapRate(recipe2) - getOverlapRate(recipe1);
        if(result == 0){
            result = recipe1.getIngredientIds().size() - recipe2.getIngredientIds().size();
        }
        return result;
    }

    private int getOverlapRate(Recipe recipe) {
        Set<Integer> tmp = new HashSet<>(criteria.getIngredients());
        tmp.retainAll(recipe.getIngredientIds());
        return tmp.size();
    }
}
