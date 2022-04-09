package com.example.remotecheck.controller;

import com.example.remotecheck.domain.Records;
import com.example.remotecheck.repository.RecordsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordsController {
    @Autowired
    private RecordsRepo repository;

    @PostMapping
    public Records setRecords(@RequestBody Records records) {
        System.out.println(records);
        return repository.save(records);
    }
    @GetMapping("/all/{id}")
    public List<Records> getAllRecords(@PathVariable String id){
       return repository.findAllByID(Long.parseLong(id));
    }
    @GetMapping("/lastRecord/{id}")
    public Records getLastRecords(@PathVariable String id){
       List<Records> list = repository.findAllByID(Long.parseLong(id));
       return list.get(list.size()-1);
    }
}
