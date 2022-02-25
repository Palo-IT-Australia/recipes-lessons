package au.palo.it.recipesapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "recipe_steps")
@NoArgsConstructor
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Column
    @Getter
    private String description;

    @ManyToOne
    @Getter
    private Recipe recipe;

    public static RecipeStep create(String description) {
        var step = new RecipeStep();
        step.description = description;
        return step;
    }
}
