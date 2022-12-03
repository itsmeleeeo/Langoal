package com.example.langoal.web;

import com.example.langoal.entities.Appointment;
import com.example.langoal.entities.Tutor;
import com.example.langoal.entities.User;
import com.example.langoal.repository.AppointmentRepository;
import com.example.langoal.repository.TutorRepository;
import com.example.langoal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor

public class LangoalController {
    @Autowired private UserRepository userRepository;
    @Autowired private TutorRepository tutorRepository;

    @Autowired private  AppointmentRepository appointmentRepository;
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

    @GetMapping(value = "/findtutor")
    public String FindTutor(Model model) {
        List<Tutor> tutors;
        tutors = tutorRepository.findAll();
        model.addAttribute("listTutors", tutors);
        model.addAttribute("appointment", new Appointment());
        return "FindTutor";
    }
    
    @RequestMapping(value = "/findpartner", method = RequestMethod.GET)
    public String FindPartner(Model model) {
        List<User> users;

        users = userRepository.findAll();
        model.addAttribute("listUsers", users);
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
    @RequestMapping(value = "/appointment", method = RequestMethod.POST)
    public String Appointment(Model model, Appointment appointment, BindingResult bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            System.out.println("Errors: " + bindingResult.getAllErrors());
            return "ThanksBooking";
        } else {
            //appointment.setUserId(1);

            appointmentRepository.save(appointment);
            return "ThanksBooking";
        }
    }
    

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String Student(Model model, User user, BindingResult bindingResult, ModelMap mm, HttpSession session) {
        if(bindingResult.hasErrors()) {
            return "RegisterUser";
        } else {
            String hash = "$2a$10$z.ySlIolTAHlz57POccaKe5Py5";
            String password = user.getPassword();
            String language = user.getNativelanguage();
            String flag = "";
            String encodedImage = null;

            switch (language) {
                case "portuguese" -> {
                    flag = "src/main/resources/img/brazil.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    user.setImage(encodedImage);
                    break;
                }
                case "cantonese", "mandarim" -> {
                    flag = "src/main/resources/img/china.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    user.setImage(encodedImage);
                    break;
                }
                case "german" -> {
                    flag = "src/main/resources/img/german.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    user.setImage(encodedImage);
                    break;
                }
                case "italian" -> {
                    flag = "src/main/resources/img/italy.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    user.setImage(encodedImage);
                    break;
                }
                case "japanese" -> {
                    flag = "src/main/resources/img/japan.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    user.setImage(encodedImage);
                    break;
                }
                case "russian" -> {
                    flag = "src/main/resources/img/russia.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    user.setImage(encodedImage);
                    break;
                }
                case "korean" -> {
                    flag = "src/main/resources/img/southkorea.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    user.setImage(encodedImage);
                    break;
                }
                case "spanish" -> {
                    flag = "src/main/resources/img/spain.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    user.setImage(encodedImage);
                    break;
                }
                default -> {
                    flag = "src/main/resources/img/unitedkingdom.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    user.setImage(encodedImage);
                    break;
                }
            }

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
            String language = tutor.getNativelanguage();
            String flag = "";
            String encodedImage = null;

            switch (language) {
                case "portuguese" -> {
                    flag = "src/main/resources/img/brazil.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    tutor.setImage(encodedImage);
                    break;
                }
                case "cantonese", "mandarim" -> {
                    flag = "src/main/resources/img/china.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    tutor.setImage(encodedImage);
                    break;
                }
                case "german" -> {
                    flag = "src/main/resources/img/german.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    tutor.setImage(encodedImage);
                    break;
                }
                case "italian" -> {
                    flag = "src/main/resources/img/italy.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    tutor.setImage(encodedImage);
                    break;
                }
                case "japanese" -> {
                    flag = "src/main/resources/img/japan.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    tutor.setImage(encodedImage);
                    break;
                }
                case "russian" -> {
                    flag = "src/main/resources/img/russia.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    tutor.setImage(encodedImage);
                    break;
                }
                case "korean" -> {
                    flag = "src/main/resources/img/southkorea.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    tutor.setImage(encodedImage);
                    break;
                }
                case "spanish" -> {
                    flag = "src/main/resources/img/spain.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    tutor.setImage(encodedImage);
                    break;
                }
                default -> {
                    flag = "src/main/resources/img/unitedkingdom.png";
                    byte[] bytes = flag.getBytes(StandardCharsets.UTF_8);
                    encodedImage = Base64.getEncoder().encodeToString(bytes);
                    tutor.setImage(encodedImage);
                    break;
                }
            }

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
