package au.palo.it.recipesapp.recipes.service;

import au.palo.it.recipesapp.recipes.model.Recipe;
import au.palo.it.recipesapp.recipes.repository.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipesServiceImpl implements RecipesService {

    private final RecipesRepository repository;

    public RecipesServiceImpl(@Autowired RecipesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Recipe saveRecipe(String accountId, String description) {
        return null;
    }

    @Override
    public List<Recipe> getRecipes(String accountId) {
        return null;
    }

    @Override
    public void addRating(int rating, String recipeId) {

    }

    @Override
    public List<Recipe> getPopularRecipes(double minAverageRating) {
        return null;
    }
}
