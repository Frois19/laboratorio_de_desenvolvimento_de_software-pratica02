package br.com.springboot.controllers;

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

import java.util.Optional;
import java.util.List;

import br.com.springboot.DTOs.UpdateAgentDTO;
import br.com.springboot.model.Agent;
import br.com.springboot.repository.AgentRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class AgentController {
    
    @Autowired
    private AgentRepository agentRepository;

    @PostMapping("/agent")
    public ResponseEntity<String> postAgent(@RequestBody Agent agent) {
        try {
            this.agentRepository.save(agent);
            return new ResponseEntity<>(HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
        

    @GetMapping("/agent/{id}")
    public Agent getAgent(@PathVariable("id") Long id) {

        Optional<Agent> agentFind = this.agentRepository.findById(id);

        if (agentFind.isPresent()) {
            return agentFind.get();
        }
        return null;
    }

    @GetMapping("/agent")
    public List<Agent> listAgent() {
        return this.agentRepository.findAll();
    }


    @PutMapping("agent/{id}")
    public Agent putAgent(@RequestBody UpdateAgentDTO updateAgentDTO,@PathVariable("id") Long id) {

        Optional<Agent> agentFind = this.agentRepository.findById(id);

        if (agentFind.isPresent()) {
            Agent agent = agentFind.get();
            if (!updateAgentDTO.getName().isEmpty()) {
                agent.setName(updateAgentDTO.getName());
            }

            return this.agentRepository.save(agent);

        }
        return null;
    }


    @DeleteMapping("agent/{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable("id") Long id) {
        try {
            Optional<Agent> agentFind = this.agentRepository.findById(id);
            if (agentFind.isPresent()) {
                this.agentRepository.deleteById(id);
            }
            return new ResponseEntity<>(HttpStatus.OK);   
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }
}
