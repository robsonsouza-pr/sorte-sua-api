package br.com.innovate.sortesuaapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.innovate.sortesuaapi.models.Resultado;

//@NamedQueries({
//	@NamedQuery(name = "ResultadoRepository.findAll", 
//			query = "select distinct(r) from Resultado r"
//					+ " join fetch r.sorteio s "
//					+ " join fetch r.dezena dezenas")
//})
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
	
	@Query("select distinct(r) from Resultado r"
			+ " join fetch r.sorteio s "
			+ " left join fetch r.dezenas dezenas")
	List<Resultado> findAll();

}
