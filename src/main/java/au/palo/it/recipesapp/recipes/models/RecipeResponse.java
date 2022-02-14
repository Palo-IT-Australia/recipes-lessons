package au.palo.it.recipesapp.recipes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecipeResponse extends RepresentationModel<RecipeResponse> {

    private Long id;
    private String description;
    private String title;
    private List<RatingResponse> ratings;
}
