package com.example.langoal.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String confirmEmail;
    private long phone;
    private String password;
    private boolean isPremium;

//    @ManyToOne
//    private Admin admin;
}
