package com.airtonmiranda.administracaocompras.dto;

import java.io.Serializable;


import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.airtonmiranda.administracaocompras.entities.Categoria;

public class CategoriaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	public String nome;
	
	public CategoriaDTO() {
		
	}

	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
		
}
