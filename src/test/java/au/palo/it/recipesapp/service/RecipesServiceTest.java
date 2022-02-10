package au.palo.it.recipesapp.service;

import au.palo.it.recipesapp.recipes.model.Recipe;
import au.palo.it.recipesapp.recipes.repository.RecipesRepository;
import au.palo.it.recipesapp.recipes.rest.RecipeResponse;
import au.palo.it.recipesapp.recipes.service.RecipesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RecipesServiceTest {

    @Mock
    private RecipesRepository repository;

    @InjectMocks
    private RecipesServiceImpl recipesService = new RecipesServiceImpl(repository);

    @Test
    public void shouldReturnRecipeWhenSaveRecipe() {
        String accountId = "account-123";
        String description = "description";

        RecipeResponse recipe = recipesService.saveRecipe(accountId, description);

        assertEquals(recipe.getDescription(), description);
        assertNotNull(recipe.getId());
    }

    @Test
    public void shouldSaveRecipeToRepository() {
        ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
        String accountId = "account-123";
        String description = "description";

        recipesService.saveRecipe(accountId, description);

        verify(repository).save(captor.capture());
        assertEquals(captor.getValue().getDescription(), description);
    }
}
