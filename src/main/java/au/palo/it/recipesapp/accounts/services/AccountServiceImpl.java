package au.palo.it.recipesapp.accounts.services;

import au.palo.it.recipesapp.accounts.models.AccountRequest;
import au.palo.it.recipesapp.accounts.repository.AccountRepository;
import au.palo.it.recipesapp.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(@Autowired AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccount(Long id) {
        return this.accountRepository.findById(id).orElse(null);
    }

    @Override
    public void save(AccountRequest accountRequest) {
        this.accountRepository.save(mapFromAccountRequest(accountRequest));
    }

    private Account mapFromAccountRequest(AccountRequest accountRequest) {
        var account = new Account();
        account.setName(accountRequest.getName());
        account.setIsAdmin(accountRequest.getIsAdmin());
        return account;
    }
}
