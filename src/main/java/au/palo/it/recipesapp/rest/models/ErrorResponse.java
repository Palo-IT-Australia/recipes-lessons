package au.palo.it.recipesapp.rest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ErrorResponse {

    @Getter
    @Setter
    String message;
}
