package br.com.innovate.sortesuaapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.innovate.sortesuaapi.models.Aposta;


public interface ApostaRepository extends JpaRepository<Aposta, Long> {
	
	@Query("select distinct(r) from Resultado r"
			+ " join fetch r.sorteio s "
			+ " left join fetch r.dezenas dezenas")
	List<Aposta> findAll();

	@Query("select distinct(a)"
			+ " from Aposta a join fetch a.sorteio s "
			+ " join fetch a.dezenas dezenas "
			+ " where a.sorteio.loteria.id =?1  and a.sorteio.numero = ?2")
	Aposta buscarPorLoteriaENumero(Long tipoId, String numero);

}
