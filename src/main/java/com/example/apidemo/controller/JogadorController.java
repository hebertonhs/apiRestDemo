package com.example.apidemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.apidemo.model.Jogador;
import com.example.apidemo.repository.JogadorRepository;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {
	
	@Autowired
	private JogadorRepository jogadorRepository;
	
	@GetMapping(path = "/listarTodos")
	public List<Jogador> listar(){
		return jogadorRepository.findAll();
	}
	
	@GetMapping(path = "/listarPorId/{id}")
	public ResponseEntity listarPorId(@PathVariable("id") Long id){
		return jogadorRepository.findById(id)
			.map(record -> ResponseEntity.ok().body(record))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Jogador salvar(@RequestBody Jogador jogador) {
		return jogadorRepository.save(jogador);
	}
	
	@PutMapping("/{id}")
	Jogador atualizar(@RequestBody Jogador nJogador, @PathVariable Long id) {

        return jogadorRepository.findById(id)
            .map(jogador -> {
                jogador.setNome(nJogador.getNome());
                jogador.setInstagram(nJogador.getInstagram());
                jogador.setApelido(nJogador.getApelido());
                jogador.setSenha(nJogador.getSenha());
                jogador.setStatus(nJogador.getStatus());
                return jogadorRepository.save(jogador);
            })
            .orElseGet(() -> {
                return jogadorRepository.save(nJogador);
            });
    }
	
	@GetMapping(path = "/listarPorStatus/{status}")
	public ResponseEntity<List<Jogador>> listarPorStatus(@PathVariable("status") String status){
		return new ResponseEntity<List<Jogador>>(jogadorRepository.findByStatus(status), HttpStatus.OK);
	}
	

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		jogadorRepository.deleteById(id);
		return ResponseEntity.ok(id);
	}
	
}
