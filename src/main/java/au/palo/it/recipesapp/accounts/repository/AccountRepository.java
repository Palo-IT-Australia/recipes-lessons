package au.palo.it.recipesapp.accounts.repository;

import au.palo.it.recipesapp.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
