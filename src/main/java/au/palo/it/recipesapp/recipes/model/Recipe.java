package au.palo.it.recipesapp.recipes.model;

import lombok.Getter;

import java.util.*;

@Getter
public class Recipe {

    private final String id;
    private final String description;
    private final List<Rating> ratings;
    private final String accountId;

    public Recipe(String description, String accountId) {
        this.description = description;
        this.id = generateId();
        this.ratings = new ArrayList<>();
        this.accountId = accountId;
    }

    private String generateId() {
        return String.valueOf(UUID.randomUUID());
    }

    public void addRating(int rating) {
        this.ratings.add(new Rating(rating, new Date()));
    }

    public double getAverageRating() {
        return this.ratings.stream().mapToInt(Rating::getRating).average().orElse(0);
    }
}
