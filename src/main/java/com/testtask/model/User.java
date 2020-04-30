package com.testtask.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Column(name = "firstName")
    @NotEmpty(message = "Please provide a first name")
    private String firstName;

    @Column(name = "lastName")
    @NotEmpty(message = "Please provide a last name")
    private String lastName;
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail Id")
    @NotEmpty(message = "Please provide an e-mail Id")
    private String email;

    @Column(name = "password")
    @Transient
    private String password;
}
