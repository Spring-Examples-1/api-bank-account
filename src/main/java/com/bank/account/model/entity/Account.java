package com.bank.account.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bank.account.util.AccountTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="ACCOUNT")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {

    /**
     * The account id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private long accountId;
    
    /**
     * The account type declared in {@link AccountTypeEnum}
     */
    @Column(name = "ACCOUNT_TYPE", length=20,  nullable= false)
    private AccountTypeEnum accountType;

    /**
     * The account number
     */
    @Column(name = "ACCOUNT_NUMBER",  nullable= false)
    private Long accountNumber;

    /**
     * The account balance
     */
    @Column(name = "BALANCE", precision = 10, scale = 2,  nullable= false)
    private Double balance;

    /**
     * The account links for a client
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;
}