package br.com.springboot.controllers;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.DTOs.UpdtaeClientDTO;
import br.com.springboot.model.Client;
import br.com.springboot.repository.ClientRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clinet/{id}")
    public Client getClient(@PathVariable("id") Long id) {

        Optional<Client> clientFind = this.clientRepository.findById(id);
        if (clientFind.isPresent()) {
            return clientFind.get();
        }
        return null;
    }

    @GetMapping("/client")
    public List<Client> listClient() {
        return this.clientRepository.findAll();
    }

    @PostMapping("/client")
    public ResponseEntity<String> postClient(@RequestBody Client client) {
        try {
            this.clientRepository.save(client);
            return new ResponseEntity<>(HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }



    @PutMapping("client/{id}")
    public Client putClient(@RequestBody UpdtaeClientDTO updtaeClientDTO, @PathVariable("id") Long id) {

        Optional<Client> clientFind = this.clientRepository.findById(id);

        if (clientFind.isPresent()) {
            Client client = clientFind.get();
            if (!updtaeClientDTO.getName().isEmpty()) {
                client.setName(updtaeClientDTO.getName());
            }
            if (!updtaeClientDTO.getAddress().isEmpty()) {
                client.setAddress(updtaeClientDTO.getAddress());
            }
            if (!updtaeClientDTO.getProfession().isEmpty()) {
                client.setProfession(updtaeClientDTO.getProfession());
            }

            return this.clientRepository.save(client);

        }
        return null;
    }

    @DeleteMapping("client/{id}")
    public void deleteClient( @PathVariable("id") Long id) {

        Optional<Client> clientFind = this.clientRepository.findById(id);

        if (clientFind.isPresent()) {
            this.clientRepository.deleteById(id);
        }
    }

    

}
