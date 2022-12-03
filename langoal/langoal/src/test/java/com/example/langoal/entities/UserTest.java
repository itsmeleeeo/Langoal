package com.example.langoal.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user = new User();

    @BeforeEach
    public void intiEach() {
        ByteArrayInputStream in = new ByteArrayInputStream("A name".getBytes());
        System.setIn(in);
    }

    @org.junit.jupiter.api.Test
    void getFirstname() {
        assertEquals("FirstName", user.getFirstname());
    }

    @org.junit.jupiter.api.Test
    void getLastname() {
        assertEquals("LastName", user.getLastname());
    }

    @org.junit.jupiter.api.Test
    void getEmail() {
        assertEquals("john@doe.com", user.getEmail());
    }
}