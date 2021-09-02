package au.palo.it.recipesapp.recipes.service;

import au.palo.it.recipesapp.recipes.model.Recipe;

import java.util.List;

public interface RecipesService {

    Recipe saveRecipe(String accountId, String description);

    List<Recipe> getRecipes(String accountId);

    void addRating(int rating, String recipeId);

    List<Recipe> getPopularRecipes(double minAverageRating);
}
