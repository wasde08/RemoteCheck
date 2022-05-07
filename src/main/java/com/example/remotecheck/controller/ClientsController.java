package com.example.remotecheck.controller;



import com.example.remotecheck.domain.Clients;
import com.example.remotecheck.domain.Image;
import com.example.remotecheck.repository.ClientsRepo;
import com.example.remotecheck.repository.ImageRepo;
import com.example.remotecheck.util.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientsRepo repository;

    @Autowired
    private ImageRepo imageRepo;

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
    @GetMapping("/sendTelMess/{mess}")
    public void sendTelMess(@PathVariable String mess) {
        Service service = new Service();
        service.sendToTelegram("Сервер №" + mess + " не отвечает");
    }

    @PostMapping("/image")
    public void saveImage(@RequestBody Image img) {
        Clients cl = repository.findByName(img.getClients_id().getKey());
        img.setClients_id(cl);
        imageRepo.save(img);
    }
    @GetMapping("/getImage/{id}")
    public Image getImageByIdClient(@PathVariable String id) {
        System.out.println(id);
        return imageRepo.findById(Long.parseLong(id));
    }

}

