package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
    
}
