package au.palo.it.recipesapp.recipes.repository;

import au.palo.it.recipesapp.entities.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipesRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> getAllByAccountId(String accountId);

    Recipe getById(Long id);
}

