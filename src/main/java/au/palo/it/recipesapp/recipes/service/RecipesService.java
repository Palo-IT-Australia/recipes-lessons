package au.palo.it.recipesapp.recipes.service;

import au.palo.it.recipesapp.entities.Recipe;
import au.palo.it.recipesapp.recipes.models.RecipeResponse;

import java.util.List;

public interface RecipesService {

    RecipeResponse saveRecipe(String accountId, String description);

    List<RecipeResponse> getRecipes(String accountId);

    void addRating(Long recipeId, int rating, String comment);

    List<Recipe> getPopularRecipes(double minAverageRating);

    RecipeResponse getRecipe(Long id);
}
