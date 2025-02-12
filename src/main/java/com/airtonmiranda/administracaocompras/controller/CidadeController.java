package com.airtonmiranda.administracaocompras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airtonmiranda.administracaocompras.entities.Cidade;
import com.airtonmiranda.administracaocompras.services.CidadeService;

@RestController
@RequestMapping(value = "/api/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(){
		List<Cidade> cidades = cidadeService.getAll();
		return ResponseEntity.ok().body(cidades);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Integer id){
		Cidade cidade = cidadeService.getById(id);
		return ResponseEntity.ok().body(cidade);
	}
	
	@RequestMapping(value = "/estados/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCidadesByEstado(@PathVariable Integer id){
		List<Cidade> cidades = cidadeService.getCidadesByEstado(id);
		return ResponseEntity.ok().body(cidades);
	}
}
