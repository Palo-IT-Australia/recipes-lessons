package au.palo.it.recipesapp.accounts.services;

import au.palo.it.recipesapp.accounts.models.AccountRequest;
import au.palo.it.recipesapp.entities.Account;

public interface AccountService {

    Account getAccount(Long id);

    void save(AccountRequest accountRequest);
}
