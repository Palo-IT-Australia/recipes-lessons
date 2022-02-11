package au.palo.it.recipesapp.recipes.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecipeRequest {
    private String description;
    private String accountId;
}
