package ua.kiev.naiv.drinkit.cocktail.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("unused")
public abstract class RecipeSearchResultMixin {
    @JsonIgnore
    private byte[] image;
    @JsonIgnore
    private String description;
}
