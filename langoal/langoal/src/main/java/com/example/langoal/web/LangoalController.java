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

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/accountverification", method = RequestMethod.GET)
    public String EmailVerification(){
        return "AccountVerification";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String accountinfo(Model model, User user, Tutor tutor, @RequestParam(name = "email") String userEmail, String tutorEmail) {
        userEmail = user.getEmail();
        tutorEmail = tutor.getEmail();
        List<User> users;
        List<Tutor> tutors;

        users = userRepository.findUserByEmail(userEmail);
        tutors = tutorRepository.findUserByEmail(tutorEmail);

        if(users.size() == 1) {
            model.addAttribute("listUsers", users);
            return "AccountSettings";
        } else if(tutors.size() == 1){
            model.addAttribute("listUsers", tutors);
            return "AccountSettings";
        }
        return "UserAccount";
    }

    @RequestMapping(value = "/settingsverification", method = RequestMethod.GET)
    public String settingsverification() {
        return "SettingsVerification";
    }

    @RequestMapping(value = "settings", method = RequestMethod.GET)
    public String settings(Model model, User user, Tutor tutor, @RequestParam(name = "email") String userEmail, String tutorEmail) {
        userEmail = user.getEmail();
        tutorEmail = tutor.getEmail();
        List<User> users;
        List<Tutor> tutors;

        users = userRepository.findUserByEmail(userEmail);
        tutors = tutorRepository.findUserByEmail(tutorEmail);

        if(users.size() == 1) {
            model.addAttribute("listUsers", users);
            return "UserSettings";
        } else if(tutors.size() == 1){
            model.addAttribute("listUsers", tutors);
            return "TutorSettings";
        }
        return "UserAccount";
    }

    @RequestMapping(value="/passwordchange", method = RequestMethod.GET)
    public String passwordchange() {
        return "ChangePassword";
    }

    @RequestMapping(value="/deleteaccount", method = RequestMethod.GET)
    public String deleteaccount() {
        return "DeleteVerification";
    }
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String username() {

        return "Username";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Model model, User user, Tutor tutor) {
        List<User> users;
        List<Tutor> tutors;
        String userEmail = user.getEmail();
        String tutorEmail = tutor.getEmail();

        users = userRepository.findUserByEmail(userEmail);
        tutors = tutorRepository.findUserByEmail(tutorEmail);

        if(users.size() == 1) {
            User userId = users.get(0);
            long idToDelete = userId.getId();
            userRepository.deleteById(idToDelete);
        }

        if(tutors.size() == 1) {
            Tutor tutorId = tutors.get(0);
            long idToDelete = tutorId.getId();
            tutorRepository.deleteById(idToDelete);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String Chat() {
        return "Chat";
    }
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String password(Model model, User user, Tutor tutor) {
        List<User> users;
        List<Tutor> tutors;
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();
        String tutorEmail = tutor.getEmail();
        String tutorPassword = tutor.getPassword();
        String hash = "$2a$10$z.ySlIolTAHlz57POccaKe5Py5";

        users = userRepository.findUserByEmail(userEmail);
        tutors = tutorRepository.findUserByEmail(tutorEmail);


        if(users.size() == 1) {
            User passwordToChange = users.get(0);
            String newPassword = passwordToChange.getPassword();
            if(newPassword != userPassword) {
                String password = hash + userPassword;
                user.setPassword(password);
               userRepository.save(user);
            }
        } else if (tutors.size() == 1) {
            Tutor passwordToChange = tutors.get(0);
            String newPassword = passwordToChange.getPassword();
            if(newPassword != tutorPassword) {
                String password = hash + tutorPassword;
                tutor.setPassword(password);
                tutorRepository.save(tutor);
            }
        }

        return "UserAccount";
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

    @RequestMapping(value = "/username", method = RequestMethod.POST)
    public String usernmame(Model model, User user, Tutor tutor) {
        List<User> users;
        List<Tutor> tutors;
        String userEmail = user.getEmail();
        String userUsername = user.getFirstname();
        String tutorEmail = tutor.getEmail();
        String tutorUsername = tutor.getFirstname();

        users = userRepository.findUserByEmail(userEmail);
        tutors = tutorRepository.findUserByEmail(tutorEmail);


        if(users.size() == 1) {
            User usernameToChange = users.get(0);
            String newUsername = usernameToChange.getFirstname();

            if(newUsername != userUsername) {
                user.setFirstname(newUsername);
                userRepository.save(user);
            }
        } else if (tutors.size() == 1) {
            Tutor usernameToChange = tutors.get(0);
            String newUsername = usernameToChange.getFirstname();

            if(newUsername != tutorUsername) {
                tutor.setFirstname(newUsername);
                tutorRepository.save(tutor);
            }
        }

        return "UserAccount";
    }
}
