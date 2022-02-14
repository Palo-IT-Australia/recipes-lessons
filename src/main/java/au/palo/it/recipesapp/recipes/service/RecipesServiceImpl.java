package au.palo.it.recipesapp.recipes.service;

import au.palo.it.recipesapp.recipes.controllers.RecipesController;
import au.palo.it.recipesapp.entities.Rating;
import au.palo.it.recipesapp.entities.Recipe;
import au.palo.it.recipesapp.recipes.models.RecipeException;
import au.palo.it.recipesapp.recipes.repository.RecipesRepository;
import au.palo.it.recipesapp.recipes.models.RatingResponse;
import au.palo.it.recipesapp.recipes.models.RecipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class RecipesServiceImpl implements RecipesService {

    private final RecipesRepository repository;

    public RecipesServiceImpl(@Autowired RecipesRepository repository) {
        this.repository = repository;
    }

    @Override
    public RecipeResponse saveRecipe(String accountId, String description) {
        if (description != null && !description.strip().isBlank()) {
            return mapFromRecipe(this.repository.save(new Recipe(description, accountId)));
        }
        throw new RecipeException("Invalid input");
    }

    @Override
    public List<RecipeResponse> getRecipes(String accountId) {
        var recipes = this.repository.getAllByAccountId(accountId);
        return recipes.stream().map(this::mapFromRecipe).collect(Collectors.toList());
    }

    @Override
    public void addRating(Long recipeId, int rating, String comment) {
        var recipe = this.repository.getById(recipeId);
        recipe.addRating(rating, comment);
        this.repository.save(recipe);
    }

    @Override
    public List<Recipe> getPopularRecipes(double minAverageRating) {
        return null;
    }

    @Override
    public RecipeResponse getRecipe(Long id) {
        return mapFromRecipe(this.repository.getById(id));
    }

    private RecipeResponse mapFromRecipe(Recipe recipe) {
        var recipeResponse = new RecipeResponse(
                recipe.getId(),
                recipe.getDescription(),
                recipe.getTitle(),
                recipe.getRatings().stream().map(this::mapRating).collect(Collectors.toList()));

        recipeResponse.add(linkTo(RecipesController.class).slash(recipeResponse.getId()).withSelfRel());
        return recipeResponse;
    }

    private RatingResponse mapRating(Rating rating) {
        return new RatingResponse(rating.getRating(), rating.getComment());
    }
}
