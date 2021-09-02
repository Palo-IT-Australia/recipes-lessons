package au.palo.it.recipesapp.recipes.repository;

import au.palo.it.recipesapp.recipes.model.Recipe;

import java.util.List;

public interface RecipesRepository {

    Recipe save(String accountId, Recipe recipe);

    List<Recipe> getAllByAccountId(String accountId);

    Recipe getById(String recipeId);

    List<Recipe> getByMinAverageRating(double minAverageRating);
}
