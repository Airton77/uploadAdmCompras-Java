package com.airtonmiranda.administracaocompras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airtonmiranda.administracaocompras.entities.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
