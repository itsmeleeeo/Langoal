package com.example.langoal.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private long phone;
    private String password;
    private int ispremium;
    private String image;
    private String nativelanguage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Appointment> appointment;
}
