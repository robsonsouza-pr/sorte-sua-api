package br.com.innovate.sortesuaapi.repositories;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.innovate.sortesuaapi.models.Sorteio;

@NamedQueries({
		@NamedQuery(name = "SorteioRepository.findByTipoIdAndNumero", query = "select s from sorteio s where s.loteria.id = :id and s.numero :numero") })
public interface SorteioRepository extends JpaRepository<Sorteio, Long> {

	Sorteio findByLoteriaIdAndNumero(Long id, String numero);

}
