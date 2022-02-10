package au.palo.it.recipesapp.recipes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<Rating> getRatings() {
        if (this.ratings == null) {
            return new ArrayList<>();
        }
        return ratings;
    }
}
