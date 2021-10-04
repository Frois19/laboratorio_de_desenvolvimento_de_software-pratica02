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

import br.com.springboot.DTOs.UpdateCarsDTO;
import br.com.springboot.model.Cars;
import br.com.springboot.repository.CarsRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cars")
public class CarsController {
    
    @Autowired
    private CarsRepository carsRepository;

    @PostMapping()
    public ResponseEntity<String> postUser(@RequestBody Cars cars) {
        try {
            this.carsRepository.save(cars);
            return new ResponseEntity<>(HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Cars getUser(@PathVariable("id") Long id) {

        Optional<Cars> carsFind = this.carsRepository.findById(id);

        if (carsFind.isPresent()) {
            return carsFind.get();
        }
        return null;
    }

    @GetMapping()
    public List<Cars> listCars() {
        return this.carsRepository.findAll();
    }

    @PutMapping("/{id}")
    public Cars putAgent(@RequestBody UpdateCarsDTO updateCarsDTO,@PathVariable("id") Long id) {

        Optional<Cars> carsFind = this.carsRepository.findById(id);

        if (carsFind.isPresent()) {
            Cars cars = carsFind.get();
            if (!updateCarsDTO.getPlate().isEmpty()) {
                cars.setPlate(updateCarsDTO.getPlate());
            }

            return this.carsRepository.save(cars);

        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteClient( @PathVariable("id") Long id) {

        Optional<Cars> carsFind = this.carsRepository.findById(id);

        if (carsFind.isPresent()) {
            this.carsRepository.deleteById(id);
        }
    }
}
