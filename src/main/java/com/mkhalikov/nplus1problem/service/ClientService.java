package com.mkhalikov.nplus1problem.service;

import com.github.javafaker.Faker;
import com.mkhalikov.nplus1problem.model.Client;
import com.mkhalikov.nplus1problem.model.EmailAddress;
import com.mkhalikov.nplus1problem.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void generateDB() {
        List<Client> clients = create2000Clients();
        clientRepository.saveAll(clients);
    }

    public List<Client> create2000Clients() {
        List<Client> clients = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 2_000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String sufixTel = String.valueOf(i);
            String telephone = "+375290000000";

            List<EmailAddress> emailAddresses = Arrays.asList(
                    new EmailAddress((firstName + lastName).toLowerCase() + "1" + i + "@gmail.com"),
                    new EmailAddress((firstName + lastName).toLowerCase() + "2" + i + "@gmail.com"));

            telephone = telephone.substring(0, telephone.length() - sufixTel.length()) + sufixTel;
            Client client = new Client(
                    firstName + " " + lastName,
                    telephone,
                    emailAddresses
            );

            for (EmailAddress emailAddress : emailAddresses) {
                emailAddress.setClient(client);
            }

            clients.add(client);
        }
        return clients;
    }

    public List<Client> findByNameContaining(String userName) {
        return clientRepository.findByFullNameContaining(userName);
    }

}
