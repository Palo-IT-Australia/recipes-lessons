package au.palo.it.recipesapp.recipes.model;

import lombok.Getter;

import java.util.*;

@Getter
public class Recipe {

    private String id;
    private String description;
    private List<Rating> ratings;
    private String accountId;

    public Recipe(String description, String accountId) {
    }

    private String generateId() {
        return String.valueOf(UUID.randomUUID());
    }
}
