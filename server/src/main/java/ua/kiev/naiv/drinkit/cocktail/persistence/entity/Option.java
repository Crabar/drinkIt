package ua.kiev.naiv.drinkit.cocktail.persistence.entity;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "recipe_options", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "option")
})
public class Option {

    private int id;
    private String option;
    private String name;

    public Option() {
    }

    public Option(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @Column
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
