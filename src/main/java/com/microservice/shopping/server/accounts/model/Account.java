package com.microservice.shopping.server.accounts.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Entity/Model class to represent the Account
 * All the data of this class is stored in the DB.
 * With the @Table annotation we define the table name
 */
@Entity
@Getter
@Setter
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    public static Long nextId = 0L;

    @Id
    protected Long id;

    protected String number;

    //We define which column use to populate this attribute
    @Column(name = "name")
    protected String fullName;

    protected BigDecimal balance;

    /**
     * Don't use this in production, this is only for testing purpouse
     *
     * @return The next id.
     */
    protected static Long getNextId() {
        synchronized (nextId) {
            return nextId++;
        }
    }

    /**
     * We need to define the default constructor because JPA require this.
     */
    protected Account() {
        balance = BigDecimal.ZERO;
    }

    public Account(String number, String name) {
        id = getNextId();
        this.number = number;
        this.fullName = name;
        balance = BigDecimal.ZERO;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void withdraw(BigDecimal amount) {
        balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        balance.add(amount);
    }

    @Override
    public String toString() {
        return number + " (" + fullName + "): $" + balance;
    }
}
