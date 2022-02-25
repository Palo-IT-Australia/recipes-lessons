package au.palo.it.recipesapp.service;

import au.palo.it.recipesapp.entities.Recipe;
import au.palo.it.recipesapp.recipes.models.RecipeException;
import au.palo.it.recipesapp.recipes.models.RecipeRequest;
import au.palo.it.recipesapp.recipes.repository.RecipesRepository;
import au.palo.it.recipesapp.recipes.models.RecipeResponse;
import au.palo.it.recipesapp.recipes.service.RecipesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipesServiceTest {

    @Mock
    private RecipesRepository repository;

    @InjectMocks
    private RecipesServiceImpl recipesService;

    @Test
    public void shouldReturnRecipeWhenSaveRecipe() {
        when(repository.save(any(Recipe.class))).then(returnsFirstArg());
        var request = new RecipeRequest();
        request.setAccountId("123");
        request.setTitle("Banana smoothie");
        request.setSteps(Collections.singletonList("description"));

        RecipeResponse recipe = recipesService.saveRecipe(request);

        assertEquals(recipe.getSteps().get(0), "description");
    }

    @Test
    public void shouldSaveRecipeToRepository() {
        when(repository.save(any(Recipe.class))).then(returnsFirstArg());
        ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
        var request = new RecipeRequest();
        request.setAccountId("123");
        request.setTitle("Banana smoothie");
        request.setSteps(Collections.singletonList("description"));

        recipesService.saveRecipe(request);

        verify(repository).save(captor.capture());
        assertEquals(captor.getValue().getTitle(), "Banana smoothie");
    }

    @Test
    public void shouldNotSaveRecipeWithEmptyDescription() {
        var request = new RecipeRequest();
        request.setAccountId("123");
        request.setTitle(" ");

        var thrown = assertThrows(RecipeException.class, () -> recipesService.saveRecipe(request));
        assertEquals(thrown.getMessage(), "Invalid input");
    }

    @Test
    public void shouldDeleteRecipe() {
        long mockRecipeId = 1234;
        String mockUserId = "1L";

        recipesService.delete(mockRecipeId, mockUserId);

        verify(repository, times(1)).deleteById(mockRecipeId);
    }

    @Test
    public void shouldNotDeleteRecipeWhenRecipeDoesNotBelongToTheUser() {
        long mockRecipeId = 1234;
        String mockUserId = "1L";

        var recipe = new Recipe();
        recipe.setAccountId("2L");

        when(repository.getById(mockRecipeId)).thenReturn(recipe);

        assertThrows(RecipeException.class, () -> recipesService.delete(mockRecipeId, mockUserId));
        verify(repository, times(0)).deleteById(mockRecipeId);
    }

    @Test
    public void shouldThrowErrorOnDeleteWhenRecipeDoesNotBelongToUser() {
        long mockRecipeId = 1234;
        String mockUserId = "1L";

        var recipe = new Recipe();
        recipe.setAccountId("2L");

        when(repository.getById(mockRecipeId)).thenReturn(recipe);
        var exception = assertThrows(RecipeException.class, () -> recipesService.delete(mockRecipeId, mockUserId));

        assertEquals("You fucked up", exception.getMessage());
    }
}
