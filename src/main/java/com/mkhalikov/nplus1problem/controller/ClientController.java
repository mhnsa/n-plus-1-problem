package com.mkhalikov.nplus1problem.controller;

import com.mkhalikov.nplus1problem.model.Client;
import com.mkhalikov.nplus1problem.repository.ClientRepository;
import com.mkhalikov.nplus1problem.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/fillDB")
    public String fillDataBase() {
        clientService.generateDB();
        return "Amount clients: " + clientRepository.count();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Client> findByNameContaining(@RequestParam String clientName) {
        List<Client> clients = clientService.findByNameContaining(clientName);
        return clients;
    }
}
