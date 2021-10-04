package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.Cars;

public interface CarsRepository extends JpaRepository<Cars, Long> {
    
}
