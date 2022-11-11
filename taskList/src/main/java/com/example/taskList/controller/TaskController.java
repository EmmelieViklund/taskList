package com.example.taskList.controller;

import com.example.taskList.exception.ResourceNotFoundException;
import com.example.taskList.model.Task;
import com.example.taskList.repo.UserRepository;
import com.example.taskList.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.taskList.enumeration.ResponsePhrases.*;

@Controller
public class TaskController {

    @Autowired
    private UserRepository employeeRepo;

    @Autowired
    private TaskRepository taskRepo;

    @GetMapping("/")
    public String homePage() {
        return "login";
    }

    //Load tasks by employee id
    @GetMapping("/tasklist/{userId}")
    public String getAllTasksByEmployeeId(Model model, @PathVariable(value = "userId") Long userId) {
        if (!employeeRepo.existsById(userId)) {
            throw new ResourceNotFoundException(NO_TASKS_ASSIGNED + userId + WAS_FOUND);
        }
        List<Task> taskList = taskRepo.findByUserId(userId);
        model.addAttribute("taskList", taskList);
        return "start";
    }


    /*@PostMapping("/create/task/{userId}")
    public String createTask(Model model, @PathVariable(value = "userId") Long userId,
                                           @RequestBody Task taskRequest) {

        Task task = employeeRepo.findById(userId).map(user -> {
            taskRequest.setUser(user);
            return taskRepo.save(taskRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Something Went Wrong" + userId));

        model.addAttribute("addTask", task);

        return "controllertest";
    }

    @DeleteMapping("/task/delete/{id}")
    public String deleteTask(Model model, @PathVariable("id") Long id) {
         taskRepo.deleteById(id);
        return "start";
    }*/
}
