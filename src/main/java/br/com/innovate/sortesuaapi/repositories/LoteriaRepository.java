package br.com.innovate.sortesuaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.innovate.sortesuaapi.models.Loteria;


public interface LoteriaRepository extends JpaRepository<Loteria, Long> {
	
	public Loteria findByNome(String nome);

}
