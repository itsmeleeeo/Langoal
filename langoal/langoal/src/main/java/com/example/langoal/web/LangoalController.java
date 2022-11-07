package com.example.langoal.web;

import com.example.langoal.entities.Tutor;
import com.example.langoal.entities.User;
import com.example.langoal.repository.TutorRepository;
import com.example.langoal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.management.Query;
import javax.persistence.Convert;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor

public class LangoalController {
    @Autowired private UserRepository userRepository;
    @Autowired private TutorRepository tutorRepository;
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

    @RequestMapping(value = "/findtutor", method = RequestMethod.GET)
    public String FindTutor() {
        return "FindTutor";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String Settings() {
        return "UserSettings";
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

    @RequestMapping(value = "/findpartner", method = RequestMethod.GET)
    public String FindPartner(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "FindPartner";
    }

    //POST REQUESTS

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String Student(Model model, User user, BindingResult bindingResult, ModelMap mm, HttpSession session) {
        if(bindingResult.hasErrors()) {
            return "RegisterUser";
        } else {
            String hash = "$2a$10$z.ySlIolTAHlz57POccaKe5Py5";
            String password = user.getPassword();
            password = hash + password;
            user.setPassword(password);
            userRepository.save(user);
            return "redirect:/";
        }
    }
    
    @RequestMapping(value = "/tutor", method = RequestMethod.POST)
    public String Tutor(Model model, Tutor tutor, BindingResult bindingResult, ModelMap mm, HttpSession session) {
        if(bindingResult.hasErrors()) {
            return "RegisterTutor";
        } else {
            String hash = "$2a$10$z.ySlIolTAHlz57POccaKe5Py5";
            String password = tutor.getPassword();
            password = hash + password;
            tutor.setPassword(password);
            tutorRepository.save(tutor);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String LoginStudent(Model model, User user, Tutor tutor, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password, @RequestParam(defaultValue = "false", required = true, value = "return") Boolean found) {
        String hash = "$2a$10$z.ySlIolTAHlz57POccaKe5Py5";
        List<User> users;
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();
        String newUserPassword = hash + userPassword;

        users = userRepository.findUserByEmailAndPassword(userEmail, newUserPassword);

        List<Tutor> tutors;
        String tutorEmail = tutor.getEmail();
        String tutorPassword = tutor.getPassword();
        String newTutorPassword = hash + tutorPassword;

        tutors = tutorRepository.findByEmailAndPassword(tutorEmail, newTutorPassword);

        if(users.size() == 1) {
            return Dashboard();
        } else if (tutors.size() == 1) {
            return Dashboard();
        }
        return "redirect:/";
    }
}
