package com.example.remotecheck.controller;



import com.example.remotecheck.domain.Clients;
import com.example.remotecheck.repository.ClientsRepo;
import com.example.remotecheck.util.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientsRepo repository;

    @GetMapping
    public List<Clients> getClients() {
        return repository.findAll();
    }
    @GetMapping("/newKey")
    public Clients getNewKey() {
        Clients clients = new Clients();
        Service service = new Service();
        clients.setKey(service.getNewKey());
        repository.save(clients);
        return clients;
    }

}

