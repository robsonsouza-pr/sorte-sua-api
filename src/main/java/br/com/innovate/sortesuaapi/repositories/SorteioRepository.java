package br.com.innovate.sortesuaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.innovate.sortesuaapi.models.Sorteio;

public interface SorteioRepository extends  JpaRepository<Sorteio, Long> {

}
