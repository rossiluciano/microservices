package com.microservice.shopping.server.accounts.repository;

import com.microservice.shopping.server.accounts.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository interfaz to access the DB data using Spring Data JPA.
 * In this interface we define all the methods to retrieve information from DB
 */
public interface AccountRepository extends Repository<Account, Long> {

    /**
     * Method to find an Account by the accountNumber
     * @param accountNumber
     * @return the Account with the accountNumber
     */
    public Account findByNumber(String accountNumber);

    /**
     * Method to find Accounts that matches the name partially
     * @param partialName
     * @return a list with all the Account that matches
     */
    public List<Account> findByFullNameContainingIgnoreCase(String partialName);

    /**
     * Method to count how many Account are loaded in the DB.
     * This method is to show how to use a custom SQL sentence with JPA.
     * @return an int with the number of Account present in the DB
     */
    @Query("SELECT count(*) from Account")
    public int countAccounts();

}
