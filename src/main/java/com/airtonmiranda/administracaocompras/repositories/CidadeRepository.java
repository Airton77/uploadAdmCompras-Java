package com.airtonmiranda.administracaocompras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airtonmiranda.administracaocompras.entities.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
