package au.palo.it.recipesapp.recipes.controllers;

import au.palo.it.recipesapp.recipes.models.RecipeRequest;
import au.palo.it.recipesapp.recipes.models.RecipeResponse;
import au.palo.it.recipesapp.recipes.service.RecipesService;
import au.palo.it.recipesapp.rest.models.ErrorResponse;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes/")
public class RecipesController {

    private static final Logger logger = LoggerFactory.getLogger(RecipesController.class);

    private final RecipesService recipesService;

    public RecipesController(@Autowired RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponse>> recipes(@RequestParam String accountId) {
        try {
            return ResponseEntity.ok(recipesService.getRecipes(accountId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<RecipeResponse> recipe(
            @ApiParam(name = "id", example = "1", required = true)
            @PathVariable(name = "id", required = false) Long id) {
        try {
            return ResponseEntity.ok(recipesService.getRecipe(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody RecipeRequest recipeRequest) {
        try {
            recipesService.saveRecipe(recipeRequest.getAccountId(), recipeRequest.getDescription());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }
}
