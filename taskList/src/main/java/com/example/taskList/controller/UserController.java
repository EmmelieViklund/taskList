package com.example.taskList.controller;
import com.example.taskList.Service.UserService;
import com.example.taskList.exception.UserNotFoundException;
import com.example.taskList.model.User;
import com.example.taskList.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class UserController {

    /*@Autowired
    private AdminRepository adminRepo;*/

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String level1(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    //Fixa exception om user = null
    @PostMapping("/onUserLogin")
    public String onUserLogin(HttpSession session, Model model, @ModelAttribute User user) {
        User userObj;
        if (user.getUsername() != null && user.getPassword() != null) {

            try {
                userObj = userService.fetchUserByUsernameAndPassword(user.getUsername(), user.getPassword());
                model.addAttribute("user", userObj);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                return "login";
            }
            if (userObj != null) {
                session.setAttribute("user", userObj);
                return "redirect:/tasklist/" + userObj.getId();
            }
        }
        return "redirect:/";
    }

    @GetMapping("/secret")
    public String level1(HttpSession session){
        String username = (String)session.getAttribute("userId");
        if (username != null) {
            return "start";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse res){
        session.removeAttribute("user"); // this would be an ok solution
        session.invalidate(); // you could also invalidate the whole session, a new session will be created the next request
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return "redirect:/";
    }



















    /*
    //Gets all employees by admin Id
    @GetMapping("/administrators/{adminId}/employees")
    public ResponseEntity<List<Employee>> getAllEmployeesByAdministratorId(@PathVariable(value = "adminId") Long adminId) {
        if (!adminRepo.existsById(adminId)) {
            throw new ResourceNotFoundException(NO_EMPLOYEES_RESPONDS_To_Admin + adminId);
        }

        List<Employee> employees = employeeRepo.findByAdminId(adminId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //find employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeesById(@PathVariable(value = "id") long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_EMPLOYEES_WITH_ID + id));

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }*/
}
