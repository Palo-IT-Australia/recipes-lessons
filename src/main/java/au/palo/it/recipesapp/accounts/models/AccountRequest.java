package au.palo.it.recipesapp.accounts.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    private String name;

    private Boolean isAdmin;
}
