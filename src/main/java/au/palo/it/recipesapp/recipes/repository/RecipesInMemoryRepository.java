package au.palo.it.recipesapp.recipes.repository;

import au.palo.it.recipesapp.recipes.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RecipesInMemoryRepository implements RecipesRepository {

    private final Set<Recipe> recipes = new HashSet<>();

    @Override
    public Recipe save(String accountId, Recipe recipe) {
        this.recipes.add(recipe);
        return recipe;
    }

    @Override
    public List<Recipe> getAllByAccountId(String accountId) {
        return recipes.stream().filter(recipe -> recipe.getAccountId().equals(accountId)).collect(Collectors.toList());
    }

    @Override
    public Recipe getById(String recipeId) {
        return recipes.stream().filter(recipe -> recipe.getId().equalsIgnoreCase(recipeId)).findFirst().orElse(null);
    }

    @Override
    public List<Recipe> getByMinAverageRating(double minAverageRating) {
        return this.recipes.stream().filter(recipe -> recipe.getAverageRating() > minAverageRating).collect(Collectors.toList());
    }
}
