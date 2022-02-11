package au.palo.it.recipesapp.service;

import au.palo.it.recipesapp.entities.Recipe;
import au.palo.it.recipesapp.recipes.repository.RecipesRepository;
import au.palo.it.recipesapp.recipes.service.RecipesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class RecipesFunctionalTest {

    @Mock
    private RecipesRepository repository;

    @InjectMocks
    private RecipesServiceImpl recipesService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void shouldGetAllAccountRecipes() {
        when(repository.save(any(Recipe.class))).then(returnsFirstArg());

        recipesService.saveRecipe("1", "description-1");
        recipesService.saveRecipe("2", "description-2");
        recipesService.saveRecipe("1", "description-3");
        recipesService.saveRecipe("1", "description-4");

        verify(repository, times(4)).save(any());
    }

    @Test
    public void shouldAddRatingToRecipe() {
        when(repository.save(any(Recipe.class))).then(returnsFirstArg());

        var recipe = recipesService.saveRecipe("1", "description-1");

        recipesService.addRating(recipe.getId(), 4, "");

        assertEquals(recipe.getRatings().size(), 1);
        assertEquals(recipe.getRatings().get(0).getRating(), 4);
    }

    @Test
    public void shouldGetRecipesByMinAverageRating() {
        when(repository.save(any(Recipe.class))).then(returnsFirstArg());

        var recipe = recipesService.saveRecipe("1", "description-1");

        recipesService.addRating(recipe.getId(), 4, "");
        recipesService.addRating(recipe.getId(), 5, "");

        var recipe2 = recipesService.saveRecipe("2", "description-2");

        recipesService.addRating(recipe2.getId(), 4, "");
        recipesService.addRating(recipe2.getId(), 4, "");

        var result = recipesService.getPopularRecipes(4.4);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getId(), recipe.getId());
    }
}
