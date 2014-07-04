package ua.kiev.naiv.drinkit.cocktail.web.model;

import java.util.Arrays;

/**
 * @author pkolmykov
 */
public class Recipe {

    private int cocktailTypeId;
    private String description;
    private String name;
    private int[] options;
    private int[][] cocktailIngredients;
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getCocktailTypeId() {
        return cocktailTypeId;
    }

    public void setCocktailTypeId(int cocktailTypeId) {
        this.cocktailTypeId = cocktailTypeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOptions(int[] options) {
        this.options = options;
    }

    public int[] getOptions() {
        return options;
    }

    public void setCocktailIngredients(int[][] cocktailIngredients) {
        this.cocktailIngredients = cocktailIngredients;
    }

    public int[][] getCocktailIngredients() {
        return cocktailIngredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe that = (Recipe) o;

        if (cocktailTypeId != that.cocktailTypeId) return false;
        if (!description.equals(that.description)) return false;
        if (!name.equals(that.name)) return false;
        if (!Arrays.equals(options, that.options)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cocktailTypeId;
        result = 31 * result + description.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + Arrays.hashCode(options);
        return result;
    }
}
