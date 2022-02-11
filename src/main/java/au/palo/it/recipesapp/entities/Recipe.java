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
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private String description;

    @OneToMany(targetEntity = Rating.class, mappedBy = "recipe", fetch = FetchType.EAGER)
    @Setter
    private List<Rating> ratings;

    @Column
    @Getter
    @Setter
    private String accountId;

    public Recipe(String description, String accountId) {
        this.accountId = accountId;
        this.description = description;
    }

    public void addRating(int rating, String comment) {
        this.ratings.add(new Rating(rating, new Date(), comment));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(description, recipe.description) && Objects.equals(ratings, recipe.ratings) && Objects.equals(accountId, recipe.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, ratings, accountId);
    }

    public List<Rating> getRatings() {
        if (this.ratings == null) {
            return new ArrayList<>();
        }
        return ratings;
    }
}
