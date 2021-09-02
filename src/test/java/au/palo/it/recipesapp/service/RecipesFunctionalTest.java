package au.palo.it.recipesapp.service;

import au.palo.it.recipesapp.recipes.repository.RecipesInMemoryRepository;
import au.palo.it.recipesapp.recipes.service.RecipesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RecipesFunctionalTest {

    @InjectMocks
    private RecipesServiceImpl recipesService = new RecipesServiceImpl(new RecipesInMemoryRepository());

    @Test
    public void shouldGetAllAccountRecipes() {
        recipesService.saveRecipe("1", "description-1");
        recipesService.saveRecipe("2", "description-2");
        recipesService.saveRecipe("1", "description-3");
        recipesService.saveRecipe("1", "description-4");

        var recipes = recipesService.getRecipes("1");
        assertEquals(recipes.size(), 3);
    }

    @Test
    public void shouldAddRatingToRecipe() {
        var recipe = recipesService.saveRecipe("1", "description-1");

        recipesService.addRating(4, recipe.getId());

        assertEquals(recipe.getRatings().size(), 1);
        assertEquals(recipe.getRatings().get(0).getRating(), 4);
    }

    @Test
    public void shouldGetRecipesByMinAverageRating() {
        var recipe = recipesService.saveRecipe("1", "description-1");

        recipesService.addRating(4, recipe.getId());
        recipesService.addRating(5, recipe.getId());

        var recipe2 = recipesService.saveRecipe("2", "description-2");

        recipesService.addRating(4, recipe2.getId());
        recipesService.addRating(4, recipe2.getId());

        var result = recipesService.getPopularRecipes(4.4);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getId(), recipe.getId());
    }
}
