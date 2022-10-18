package com.example.langoal.controller;

import com.example.langoal.repository.TutorRepository;
import com.example.langoal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.langoal.entities.User;
import com.example.langoal.entities.Tutor;

@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor

public class LangoalController {
    @Autowired private UserRepository userRepository;
    @Autowired private TutorRepository tutorRepository;

    @GetMapping(path="/")
    public String HomePage() {
        return "index";
    }

    @GetMapping(path="/registerstudent")
    public String RegisterStudent() {
        return "RegisterUser";
    }

    @GetMapping(path = "/registertutor")
    public String RegisterTutor() {
        return "RegisterTutor";
    }

    @GetMapping(path = "/dashboardaccount")
    public String DashBoard() {
        return "UserAccount";
    }
}
