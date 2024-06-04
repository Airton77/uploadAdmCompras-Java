package com.airtonmiranda.administracaocompras.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtonmiranda.administracaocompras.entities.Cidade;
import com.airtonmiranda.administracaocompras.entities.Estado;
import com.airtonmiranda.administracaocompras.repositories.CidadeRepository;
import com.airtonmiranda.administracaocompras.repositories.EstadoRepository;
import com.airtonmiranda.administracaocompras.services.exception.ObjectNotFoundException;


@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Cidade> getAll(){
		List<Cidade> cidades = cidadeRepository.findAll();
		return cidades;
	}
	
	public Cidade getById(Integer id){
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		return cidade.orElseThrow(() -> new ObjectNotFoundException(
				"Cidade não encontrada!"));
	}
	
	public List<Cidade> getCidadesByEstado(Integer id){
		Optional<Estado> estado = estadoRepository.findById(id);
		List<Cidade> cidades = estado.get().getCidades();
		return cidades;
	}
}
