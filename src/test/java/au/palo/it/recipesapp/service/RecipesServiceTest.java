package au.palo.it.recipesapp.service;

import au.palo.it.recipesapp.entities.Recipe;
import au.palo.it.recipesapp.recipes.models.RecipeException;
import au.palo.it.recipesapp.recipes.repository.RecipesRepository;
import au.palo.it.recipesapp.recipes.models.RecipeResponse;
import au.palo.it.recipesapp.recipes.service.RecipesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipesServiceTest {

    @Mock
    private RecipesRepository repository;

    @InjectMocks
    private RecipesServiceImpl recipesService;

    @Test
    public void shouldReturnRecipeWhenSaveRecipe() {
        when(repository.save(any(Recipe.class))).then(returnsFirstArg());
        String accountId = "account-123";
        String description = "description";

        RecipeResponse recipe = recipesService.saveRecipe(accountId, description);

        assertEquals(recipe.getDescription(), description);
    }

    @Test
    public void shouldSaveRecipeToRepository() {
        when(repository.save(any(Recipe.class))).then(returnsFirstArg());
        ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
        String accountId = "account-123";
        String description = "description";

        recipesService.saveRecipe(accountId, description);

        verify(repository).save(captor.capture());
        assertEquals(captor.getValue().getDescription(), description);
    }

    @Test
    public void shouldNotSaveRecipeWithEmptyDescription() {
        String accountId = "account-123";
        String description = " ";

        var thrown = assertThrows(RecipeException.class, () -> recipesService.saveRecipe(accountId, description));
        assertEquals(thrown.getMessage(), "Invalid input");
    }
}
