package com.example.taskList.controller;

import com.example.taskList.exception.ResourceNotFoundException;
import com.example.taskList.model.Task;
import com.example.taskList.repo.EmployeeRepository;
import com.example.taskList.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.taskList.enumeration.ResponsePhrases.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private TaskRepository taskRepo;


    //Load tasks by employee id
    @GetMapping("/employees/{employeeId}/tasks")
    public ResponseEntity<List<Task>> getAllTasksByEmployeeId(@PathVariable(value = "employeeId") Long employeeId) {
        if (!employeeRepo.existsById(employeeId)) {
            throw new ResourceNotFoundException(NO_TASKS_ASSIGNED + employeeId + WAS_FOUND);
        }

        List<Task> tasks = taskRepo.findByEmployeeId(employeeId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    //create task by employee id
    @PostMapping("/employees/{employeeId}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable(value = "employeeId") Long employeeId,
                                           @RequestBody Task taskRequest) {

        Task task = employeeRepo.findById(employeeId).map(employee -> {
            taskRequest.setEmployee(employee);
            return taskRepo.save(taskRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(SOMETHING_WENT_WRONG + employeeId));

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    //get tasks by employee id
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTasksByEmployeeId(@PathVariable(value = "id") Long id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_TASKS_ASSIGNED + id + WAS_FOUND));

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    //update tasks by employee id
    @PutMapping("/tasks/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long id, @RequestBody Task taskRequest) {
        Task _task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_TASKS + id + WAS_FOUND));

        _task.setCustomerName(taskRequest.getCustomerName());
        _task.setGlobalCaseId(taskRequest.getGlobalCaseId());
        _task.setTaskType(_task.getTaskType());
        _task.setCaseLogs(taskRequest.getCaseLogs());
        _task.setMarkedDone(taskRequest.isMarkedDone());
        _task.setUpdatedAt(taskRequest.getUpdatedAt());

        return new ResponseEntity<>(taskRepo.save(_task), HttpStatus.OK);
    }

    //delete task by employeeId
    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") Long id) {
        taskRepo.deleteTaskById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
