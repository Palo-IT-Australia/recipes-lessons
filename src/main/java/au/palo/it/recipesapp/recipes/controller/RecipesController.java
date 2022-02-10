package au.palo.it.recipesapp.recipes.controller;

import au.palo.it.recipesapp.recipes.rest.RecipeRequest;
import au.palo.it.recipesapp.recipes.rest.RecipeResponse;
import au.palo.it.recipesapp.recipes.service.RecipesService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipes/")
public class RecipesController {

    private final RecipesService recipesService;

    public RecipesController(@Autowired RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping
    public CollectionModel<RecipeResponse> recipes(@RequestParam String accountId) {
        try {
            return CollectionModel.of(recipesService.getRecipes(accountId), Link.of("/recipes"));
        } catch (Exception e) {
            return CollectionModel.empty();
        }
    }

    @GetMapping("{id}")
    public RepresentationModel<?> recipe(
            @ApiParam(name = "id", example = "1", required = true)
            @PathVariable(name = "id", required = false) Long id) {
        try {
            return CollectionModel.of(recipesService.getRecipe(id));
        } catch (Exception e) {
            return CollectionModel.empty();
        }
    }

    @PostMapping
    public RepresentationModel<RecipeResponse> save(@RequestBody RecipeRequest recipeRequest) {
        return recipesService.saveRecipe(recipeRequest.getAccountId(), recipeRequest.getDescription());
    }
}
