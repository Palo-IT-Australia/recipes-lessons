package au.palo.it.recipesapp.accounts.controllers;

import au.palo.it.recipesapp.accounts.models.AccountRequest;
import au.palo.it.recipesapp.accounts.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    private final AccountService accountService;

    public AccountsController(@Autowired AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody AccountRequest accountRequest) {
        try {
            this.accountService.save(accountRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
