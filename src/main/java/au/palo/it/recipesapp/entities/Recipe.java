package au.palo.it.recipesapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "recipes")
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Column
    @Getter
    @Setter
    private String title;

    @OneToMany(targetEntity = Rating.class, mappedBy = "recipe", fetch = FetchType.LAZY)
    @Setter
    private List<Rating> ratings;

    @Column
    @Getter
    @Setter
    private String accountId;

    @OneToMany(targetEntity = RecipeStep.class)
    @Setter
    @Getter
    private List<RecipeStep> steps;

    @OneToMany(targetEntity = Ingredient.class)
    @Getter
    @Setter
    private List<Ingredient> ingredients;

    public void addRating(int rating, String comment) {
        this.ratings.add(new Rating(rating, new Date(), comment));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(ratings, recipe.ratings) && Objects.equals(accountId, recipe.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ratings, accountId);
    }

    public List<Rating> getRatings() {
        if (this.ratings == null) {
            return new ArrayList<>();
        }
        return ratings;
    }
}
