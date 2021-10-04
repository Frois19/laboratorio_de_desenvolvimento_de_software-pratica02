package br.com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
import java.util.List;

import br.com.springboot.model.Request;
import br.com.springboot.repository.RequestRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private RequestRepository requestRepository;

    @PostMapping()
    public Request postRequest(@RequestBody Request request) {
        return this.requestRepository.save(request);
    }

    @GetMapping("/{id}")
    public Request getRequest(@PathVariable("id") Long id) {

        Optional<Request> requestFind = this.requestRepository.findById(id);
        if (requestFind.isPresent()) {
            return requestFind.get();
        }
        return null;
    }

    @GetMapping()
    public List<Request> listRequest() {
        return this.requestRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteRequest(@PathVariable("id") Long id) {

        Optional<Request> requestFind = this.requestRepository.findById(id);

        if (requestFind.isPresent()) {
            this.requestRepository.deleteById(id);
        }
    }

}
