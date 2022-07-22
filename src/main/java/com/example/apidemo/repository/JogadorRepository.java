package com.example.apidemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apidemo.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long>{

	List<Jogador> findByStatus(String status);

}
