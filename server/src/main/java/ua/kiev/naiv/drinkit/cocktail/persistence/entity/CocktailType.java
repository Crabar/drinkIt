package ua.kiev.naiv.drinkit.cocktail.persistence.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
@Table(name = "recipe_types")
public class CocktailType implements Serializable {

    private Integer id;
    private String name;
    private Set<Recipe> recipeEntities;

    public CocktailType() {
    }

    public CocktailType(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "cocktailType")
    public Set<Recipe> getRecipeEntities() {
        return recipeEntities;
    }

    public void setRecipeEntities(Set<Recipe> recipeEntities) {
        this.recipeEntities = recipeEntities;
    }
}
