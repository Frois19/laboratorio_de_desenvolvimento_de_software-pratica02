package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    
}
