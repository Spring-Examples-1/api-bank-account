package com.bank.account.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Rached
 *
 */
@Entity
@Table(name="CLIENT")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Client {

    /**
     * The client id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private long clientId;

    /**
     * The Client user name
     */
    @Column(name = "USER_NAME", length = 50, nullable= false)
    private String userName;
    
    /**
     * The Client first name
     */
    @Column(name = "FIRST_NAME", length = 50, nullable= false)
    private String firstName;

    /**
     * The Client last name
     */
    @Column(name = "LAST_NAME", length = 50, nullable= false)
    private String lastName;

}