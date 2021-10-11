package br.com.anderson.southsystem.desafiobackvotos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.southsystem.desafiobackvotos.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {

}
