package au.palo.it.recipesapp.recipes.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class RecipeRequest {
    private String accountId;
    private List<String> steps;
    private List<IngredientRequest> ingredients;
    private String title;
}
