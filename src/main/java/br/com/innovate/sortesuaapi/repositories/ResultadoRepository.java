package br.com.innovate.sortesuaapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.innovate.sortesuaapi.models.Resultado;

//@NamedQueries({
//	@NamedQuery(name = "ResultadoRepository.pegaNoMeu", 
//			query = "select distinct(r) from Resultado r"
//					+ " join fetch r.sorteio s "
//					+ " join fetch r.dezena dezenas"
//					+ "where r.sorteio.tipo.id = :tipoId and r.sorteio.numero = :numero")
//})
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
	
	@Query("select distinct(r) from Resultado r"
			+ " join fetch r.sorteio s "
			+ " left join fetch r.dezenas dezenas"
			+ " order by r.sorteio.numero")
	List<Resultado> findAll();

	@Query("select distinct(r) from Resultado r join fetch r.sorteio s  join fetch r.dezenas dezenas "
			+ "where r.sorteio.loteria.id =?1  and r.sorteio.numero = ?2")
	Resultado pegaNoMeu(Long tipoId, String numero);

}
