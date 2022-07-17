package com.example.apidemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.apidemo.model.Cliente;
import com.example.apidemo.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clientRepository;
	
	@GetMapping
	public String hello() {
		return "Olá Mundo";
	}
	
	@GetMapping(path = "/listarTodos")
	public List<Cliente> listar(){
		return clientRepository.findAll();
	}
	
	@GetMapping(path = "/listarPorId/{id}")
	public ResponseEntity listarPorId(@PathVariable("id") Long id){
		return clientRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody Cliente cliente) {
		return clientRepository.save(cliente);
	}

}
