package au.palo.it.recipesapp.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "recipe_ingredients")
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Column
    @Getter
    @Setter
    private String title;

    @Column
    @Getter
    @Setter
    private String amount;

    @ManyToOne
    @Getter
    @Setter
    private Recipe recipe;

    public Ingredient(String title, String amount) {
        this.amount = amount;
        this.title = title;
    }
}
