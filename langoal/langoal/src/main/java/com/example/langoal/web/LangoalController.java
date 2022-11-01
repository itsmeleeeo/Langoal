package com.example.langoal.web;

import com.example.langoal.entities.Tutor;
import com.example.langoal.entities.User;
import com.example.langoal.repository.TutorRepository;
import com.example.langoal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.management.Query;
import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor

public class LangoalController {
    @Autowired private UserRepository userRepository;
    @Autowired private TutorRepository tutorRepository;

    @Autowired private PasswordEncoder passwordEncoder;
    static int num = 0;

    //GET REQUESTS
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String HomePage() {
        return "index";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String Dashboard() {
        return "UserAccount";
    }

    @RequestMapping(value="/student", method = RequestMethod.GET)
    public String RegisterStudent(Model model) {
        model.addAttribute("user", new User());
        return "RegisterUser";
    }

    @RequestMapping(value = "/tutor", method = RequestMethod.GET)
    public String RegisterTutor(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "RegisterTutor";
    }

    //POST REQUESTS

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String Student(Model model, User user, BindingResult bindingResult, ModelMap mm, HttpSession session) {
        if(bindingResult.hasErrors()) {
            return "RegisterUser";
        } else {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return "redirect:/";
        }
    }
    
    @RequestMapping(value = "/tutor", method = RequestMethod.POST)
    public String Tutor(Model model, Tutor tutor, BindingResult bindingResult, ModelMap mm, HttpSession session) {
        if(bindingResult.hasErrors()) {
            return "RegisterTutor";
        } else {
            String password = tutor.getPassword();
            tutor.setPassword(passwordEncoder.encode(password));
            tutorRepository.save(tutor);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(Model model, User user, Tutor tutor, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password,@RequestParam(defaultValue = "false", required = true, value = "return") Boolean found) {
        email = user.getEmail();
        password = user.getPassword();

        String userEmail = userRepository.findUserByEmail(email).toString();
        String userPassword = userRepository.findUserByPassword(password).toString();
        if(email !=  userEmail|| password != userPassword) {
            return "redirect:/";
        } else if (email.equals(userEmail) && password.equals(userPassword)) {
            found = true;
        }
        return Dashboard();
    }
}
