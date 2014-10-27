package ua.kiev.naiv.drinkit.cocktail.persistence.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Cacheable
@Table(name = "recipes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "name")
})
public class RecipeEntity implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private CocktailType cocktailType;
    private List<IngredientWithQuantity> ingredientsWithQuantities;
    private List<Option> options;
    private byte[] image;
    private byte[] thumbnail;
    private List<RecipeStatistics> recipeStatistics = new ArrayList<>();

    @Transient
    public List<Integer> getIngredientIds() {
        return getIngredientsWithQuantities().stream()
                .map(ingredientWithQuantity -> ingredientWithQuantity.getIngredient().getId())
                .collect(Collectors.toList());
    }

    @Id
    @GeneratedValue()
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne()
    @JoinColumn(name = "type_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    public CocktailType getCocktailType() {
        return cocktailType;
    }

    public void setCocktailType(CocktailType cocktailType) {
        this.cocktailType = cocktailType;
    }

    @OneToMany(mappedBy = "cocktailIngredientId.recipeEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<IngredientWithQuantity> getIngredientsWithQuantities() {
        return ingredientsWithQuantities;
    }

    public void setIngredientsWithQuantities(List<IngredientWithQuantity> ingredientsWithQuantities) {
        this.ingredientsWithQuantities = ingredientsWithQuantities;
    }

    @ManyToMany()
    @JoinTable(name = "recipes_has_options",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column()
    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    @OneToMany(mappedBy = "recipeEntity", cascade = CascadeType.REMOVE)
    public List<RecipeStatistics> getRecipeStatistics() {
        return recipeStatistics;
    }

    public void setRecipeStatistics(List<RecipeStatistics> recipeStatistics) {
        this.recipeStatistics = recipeStatistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeEntity that = (RecipeEntity) o;

        if (!cocktailType.equals(that.cocktailType)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!id.equals(that.id)) return false;
        if (!Arrays.equals(image, that.image)) return false;
        if (ingredientsWithQuantities != null ? !ingredientsWithQuantities.equals(that.ingredientsWithQuantities) : that.ingredientsWithQuantities != null)
            return false;
        if (!name.equals(that.name)) return false;
        if (options != null ? !options.equals(that.options) : that.options != null) return false;
        if (!Arrays.equals(thumbnail, that.thumbnail)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + cocktailType.hashCode();
        result = 31 * result + (ingredientsWithQuantities != null ? ingredientsWithQuantities.hashCode() : 0);
        result = 31 * result + (options != null ? options.hashCode() : 0);
        result = 31 * result + (image != null ? Arrays.hashCode(image) : 0);
        result = 31 * result + (thumbnail != null ? Arrays.hashCode(thumbnail) : 0);
        return result;
    }
}
