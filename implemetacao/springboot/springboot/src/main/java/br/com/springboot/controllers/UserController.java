package br.com.springboot.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.DTOs.UpdateAgentDTO;
import br.com.springboot.DTOs.UpdtaeClientDTO;
import br.com.springboot.model.Agent;
import br.com.springboot.model.Client;
import br.com.springboot.repository.AgentRepository;
import br.com.springboot.repository.ClientRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AgentRepository agentRepository;

    @PostMapping("/client")
    public Client postClient(@RequestBody Client client) {
        return this.clientRepository.save(client);
    }

    @PostMapping("/agent")
    public Agent postAgent(@RequestBody Agent agent) {
        return this.agentRepository.save(agent);
    }

    @GetMapping("/clinet/{id}")
    public Client getClient(@PathVariable("id") Long id) {

        Optional<Client> clientFind = this.clientRepository.findById(id);
        if (clientFind.isPresent()) {
            return clientFind.get();
        }
        return null;
    }

    @GetMapping("/agent/{id}")
    public Agent getAgent(@PathVariable("id") Long id) {

        Optional<Agent> agentFind = this.agentRepository.findById(id);

        if (agentFind.isPresent()) {
            return agentFind.get();
        }
        return null;
    }

    @PutMapping("client/{id}")
    public Client putClient(@RequestBody UpdtaeClientDTO updtaeClientDTO, @PathVariable("id") Long id) {

        Optional<Client> clientFind = this.clientRepository.findById(id);

        if (clientFind.isPresent()) {
            Client client = clientFind.get();
            if (!updtaeClientDTO.getNome().isEmpty()) {
                client.setNome(updtaeClientDTO.getNome());
            }
            if (!updtaeClientDTO.getEndereco().isEmpty()) {
                client.setEndereco(updtaeClientDTO.getEndereco());
            }
            if (!updtaeClientDTO.getProfissao().isEmpty()) {
                client.setProfissao(updtaeClientDTO.getProfissao());
            }

            return this.clientRepository.save(client);

        }
        return null;
    }


    @PutMapping("agent/{id}")
    public Agent putAgent(@RequestBody UpdateAgentDTO updateAgentDTO,@PathVariable("id") Long id) {

        Optional<Agent> agentFind = this.agentRepository.findById(id);

        if (agentFind.isPresent()) {
            Agent agent = agentFind.get();
            if (!updateAgentDTO.getEndereco().isEmpty()) {
                agent.setEndereco(updateAgentDTO.getEndereco());
            }
            if (!updateAgentDTO.getNome().isEmpty()) {
                agent.setNome(updateAgentDTO.getNome());
            }

            return this.agentRepository.save(agent);

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

    @DeleteMapping("agent/{id}")
    public void deleteAgent(@PathVariable("id") Long id) {

        Optional<Agent> agentFind = this.agentRepository.findById(id);

        if (agentFind.isPresent()) {
            this.agentRepository.deleteById(id);
        }
    }

}
