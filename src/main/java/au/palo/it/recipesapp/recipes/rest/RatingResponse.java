package au.palo.it.recipesapp.recipes.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RatingResponse {

    private int rating;
    private String comment;
}
