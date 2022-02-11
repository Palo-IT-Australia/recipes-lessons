package au.palo.it.recipesapp.recipes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RatingResponse {

    private int rating;
    private String comment;
}
