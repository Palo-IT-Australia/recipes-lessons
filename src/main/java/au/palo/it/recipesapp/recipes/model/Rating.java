package au.palo.it.recipesapp.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Rating {

    private int rating;
    private Date ratedAt;
}
