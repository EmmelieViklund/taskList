package com.example.taskList.controller;

import com.example.taskList.exception.ResourceNotFoundException;
import com.example.taskList.model.Task;
import com.example.taskList.repo.EmployeeRepository;
import com.example.taskList.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.taskList.enumeration.ResponsePhrases.*;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class TaskController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private TaskRepository taskRepo;

    @GetMapping("")
    public String homePage() {
        return "login";
    }

    //Load tasks by employee id
    @GetMapping("/employees/{employeeId}/tasks")
    public String getAllTasksByEmployeeId(Model model, @PathVariable(value = "employeeId") Long employeeId) {
        if (!employeeRepo.existsById(employeeId)) {
            throw new ResourceNotFoundException(NO_TASKS_ASSIGNED + employeeId + WAS_FOUND);
        }
        List<Task> taskList = taskRepo.findByEmployeeId(employeeId);
        model.addAttribute("taskList", taskList);
        return "start";
    }


    @PostMapping("/employees/{employeeId}/tasks")
    public String createTask(Model model, @PathVariable(value = "employeeId") Long employeeId,
                                           @RequestBody Task taskRequest) {

        Task task = employeeRepo.findById(employeeId).map(employee -> {
            taskRequest.setEmployee(employee);
            return taskRepo.save(taskRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Something Went Wrong" + employeeId));

        model.addAttribute("addTask", task);

        return "start";
    }

    @DeleteMapping("/task/delete/{id}")
    public String deleteTask(Model model, @PathVariable("id") Long id) {
        taskRepo.deleteById(id);
        return "start";
    }
}
