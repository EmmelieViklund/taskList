package com.example.taskList.controller;

import com.example.taskList.exception.ResourceNotFoundException;
import com.example.taskList.model.Task;
import com.example.taskList.model.User;
import com.example.taskList.repo.UserRepository;
import com.example.taskList.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.taskList.enumeration.ResponsePhrases.*;

@Controller
public class TaskController {

    @Autowired
    private UserRepository employeeRepo;

    @Autowired
    private TaskRepository taskRepo;

    //Load tasks by employee id
    @GetMapping("/tasklist/{userId}")
    public String getAllTasksByEmployeeId(Model model, @PathVariable(value = "userId") Long userId) {
        if (!employeeRepo.existsById(userId)) {
            throw new ResourceNotFoundException(NO_TASKS_ASSIGNED + userId + WAS_FOUND);
        }
        List<Task> taskList = taskRepo.findByUserId(userId);
        Task task = new Task();

        model.addAttribute("task", task);
        model.addAttribute("taskList", taskList);
        return "start";
    }

    @PostMapping("/delete/task")
    String deleteTask(HttpSession session, @RequestParam Long id) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        taskRepo.deleteById(id);
        return "redirect:/tasklist/" + userId;
    }

    @PostMapping("/create/task")
    public String createTask(HttpSession session, @ModelAttribute Task task) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        task.setUser(user);
        taskRepo.save(task);
        return "redirect:/tasklist/" + userId;
    }

    @PostMapping("/edit/task")
    public String editTask(Model model, HttpSession session, @ModelAttribute Task task, @RequestParam Long id) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        task.setUser(user);
        taskRepo.save(task);
        return "redirect:/tasklist/" + userId;
    }

    @PostMapping("/complete/task")
    String completeTask(Model model, HttpSession session, @RequestParam Long id) {
        User user = (User)session.getAttribute("user");
        Long userId = user.getId();

        Task task = taskRepo.findById(id).orElse(null);

        boolean taskSuccessfullyCompleted;
        if(task != null && taskRepo.findById(task.getId()).isPresent()) {
            if(taskRepo.findById(task.getId()).get().getCompletionAt() == null) {
                task.setCompletionAt(LocalDateTime.now());
            } else {
                task.setCompletionAt(null);
            }
            taskSuccessfullyCompleted = true;
            taskRepo.save(task);
        } else {
            taskSuccessfullyCompleted = false;
        }
        model.addAttribute("taskSuccessfullyCompleted", taskSuccessfullyCompleted);
        return "redirect:/tasklist/" + userId;
    }
}
