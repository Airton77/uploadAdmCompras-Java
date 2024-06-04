package com.airtonmiranda.administracaocompras.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtonmiranda.administracaocompras.entities.Estado;
import com.airtonmiranda.administracaocompras.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> getAll(){
		List<Estado> estados = estadoRepository.findAll();
		return estados;
	}
}
