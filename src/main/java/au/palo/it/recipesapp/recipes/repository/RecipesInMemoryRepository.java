package au.palo.it.recipesapp.recipes.repository;

import au.palo.it.recipesapp.recipes.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RecipesInMemoryRepository implements RecipesRepository {

    private final Set<Recipe> recipes = new HashSet<>();

    @Override
    public Recipe save(String accountId, Recipe recipe) {
        return null;
    }

    @Override
    public List<Recipe> getAllByAccountId(String accountId) {
        return null;
    }

    @Override
    public Recipe getById(String recipeId) {
        return null;
    }

    @Override
    public List<Recipe> getByMinAverageRating(double minAverageRating) {
        return null;
    }
}
