package br.com.anderson.southsystem.desafiobackvotos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>{

}
