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

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String Account() {
        return "AccountSettings";
    }

    @RequestMapping(value = "/premium", method = RequestMethod.GET)
    public String Premium() {
        return "PremiumAccount";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String Dashboard() {
        return "UserAccount";
    }

    @RequestMapping(value = "/findtutor", method = RequestMethod.GET)
    public String FindTutor(Model model) {
        List<Tutor> tutors;
        tutors = tutorRepository.findAll();
        model.addAttribute("listTutors", tutors);
        return "FindTutor";
    }
    
    @RequestMapping(value = "/findpartner", method = RequestMethod.GET)
    public String FindPartner(Model model) {
        model.addAttribute("user", new User());
        return "FindPartner";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String UserSettings() {
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

    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public String EmailVerification(){
        return "EmailVerification";
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
    public String Login(Model model, User user, Tutor tutor, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password, String firstname, @RequestParam(defaultValue = "false", required = true, value = "return") Boolean found) {
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
            return "UserAccount";
        } else if (tutors.size() == 1) {
            return "UserAccount";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String accountinfo(Model model, User user, Tutor tutor, @RequestParam(name = "email") String email) {
        email = user.getEmail();
        String hash = "$2a$10$z.ySlIolTAHlz57POccaKe5Py5";
        List<User> users;
        List<Tutor> tutors;
        String password;
        Long phone;
        String language;

        users = userRepository.findUserByEmail(email);
        tutors = tutorRepository.findUserByEmail(email);

        if(users.size() == 1) {
            model.addAttribute("listUsers", users);
            return "AccountSettings";
        } else if(tutors.size() == 1){
            model.addAttribute("listUsers", tutors);
            return "AccountSettings";
        }

        if(users.size() == 1) {
            password = user.getPassword();
            phone = user.getPhone();
            language = user.getNativelanguage();
            password = hash + password;
            user.setPassword(password);
            userRepository.save(user);

        } else {
            password = tutor.getPassword();
            phone = tutor.getPhone();
            language = tutor.getNativelanguage();
            password = hash + password;
            tutor.setPassword(password);
            tutorRepository.save(tutor);
        }
        return "UserAccount";
    }
}
