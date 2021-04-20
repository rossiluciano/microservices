package com.microservice.shopping.server.accounts.controller;

import com.microservice.shopping.server.accounts.exception.AccountNotFoundException;
import com.microservice.shopping.server.accounts.model.Account;
import com.microservice.shopping.server.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Controller class in charge of responds all the request for the service
 */
@RestController
public class AccountsController {
    protected Logger logger = Logger.getLogger(AccountsController.class
            .getName());
    protected AccountRepository accountRepository;

    /**
     * Constructor with Autowired parameter
     * @param accountRepository
     */
    @Autowired
    public AccountsController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Get an account with the specified account number.
     *
     * @param accountNumber
     *            A numeric, 9 digit account number.
     * @return The account if found.
     * @throws AccountNotFoundException
     *             If the number is not recognised.
     */
    @RequestMapping("/accounts/{accountNumber}")
    public Account byNumber(@PathVariable("accountNumber") String accountNumber) {

        logger.info("accounts-service byNumber() invoked: " + accountNumber);
        Account account = accountRepository.findByNumber(accountNumber);
        logger.info("accounts-service byNumber() found: " + account);

        if (account == null)
            throw new AccountNotFoundException(accountNumber);
        else {
            return account;
        }
    }

    /**
     * Return all the Accounts that partial matches <code> case-insensitive</code>with the name
     * E.g. the URL <code>http://.../accounts/fullname/a</code> will returns all accountswith upper or lower 'a'
     * in their full name.
     *
     * @param partialName
     * @return A non-null, non-empty set of accounts.
     * @throws AccountNotFoundException If any account matches.
     */
    @RequestMapping("/accounts/fullname/{name}")
    public List<Account> byOwner(@PathVariable("name") String partialName) {
        logger.info("accounts-service byFullName() invoked: "
                + accountRepository.getClass().getName() + " for "
                + partialName);

        List<Account> accounts = accountRepository
                .findByFullNameContainingIgnoreCase(partialName);
        logger.info("accounts-service byFullName() found: " + accounts);

        if (accounts == null || accounts.size() == 0)
            throw new AccountNotFoundException(partialName);
        else {
            return accounts;
        }
    }
}
