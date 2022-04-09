package com.example.remotecheck.controller;

import com.example.remotecheck.domain.Clients;
import com.example.remotecheck.domain.Tasks;
import com.example.remotecheck.repository.ClientsRepo;
import com.example.remotecheck.repository.TasksRepo;
import com.example.remotecheck.util.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TasksRepo repository;


    @GetMapping("/getTasksByClient/{key}")
    public List<Tasks> getNewKey(@PathVariable String key) {
        System.out.println(key);
        List<Tasks> list = repository.findCommandsByKey(key);

        return list;
    }
    @PutMapping("/changeStatus")
    public Tasks getNewKey(@RequestBody Tasks tasks) {
        tasks.setStatus(true);
        return repository.update(tasks);
    }
}
