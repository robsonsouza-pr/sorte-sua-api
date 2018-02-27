package br.com.innovate.sortesuaapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.innovate.sortesuaapi.dtos.RelatorioDto;
import br.com.innovate.sortesuaapi.models.Aposta;

//@NamedNativeQuery(name="ApostaRepository.countBySorteioId",
//query = "select d.valor, count(*) " + 
//		" from sorteio s inner join resultado r on r.sorteio_id = s.id " + 
//		" inner join resultado_dezenas rd on rd.resultados_id = r.id  " + 
//		" inner join dezena d on d.id = rd.dezenas_id " + 
//		" where loteria_id =?1 " + 
//		" group by d.valor  " + 
//		" order by 2 desc,1 asc", resultSetMapping="ApostaRepository.countBySorteioId")
//
//@SqlResultSetMapping(name = "ApostaRepository.countBySorteioId", 
//	classes = {
//		@ConstructorResult ( targetClass = RelatorioDto.class,
//					columns = {
//								@ColumnResult(name = "quantidade", type = long.class),
//								@ColumnResult(name = "valor", type = long.class) 
//			}) 
//		})

public interface ApostaRepository extends JpaRepository<Aposta, Long> {

	@Query("select distinct(r) from Resultado r" + " join fetch r.sorteio s " + " left join fetch r.dezenas dezenas")
	List<Aposta> findAll();

	@Query("select distinct(a)" + " from Aposta a join fetch a.sorteio s " + " join fetch a.dezenas dezenas "
			+ " where a.sorteio.loteria.id =?1  and a.sorteio.numero = ?2")
	Aposta buscarPorLoteriaENumero(Long tipoId, String numero);

	@Query(value = "select d.valor, count(*) " + 
			" from sorteio s inner join resultado r on r.sorteio_id = s.id " + 
			" inner join resultado_dezenas rd on rd.resultados_id = r.id  " + 
			" inner join dezena d on d.id = rd.dezenas_id " + 
			" where loteria_id =?1 " + 
			" group by d.valor  " + 
			" order by 2 desc,1 asc", nativeQuery = true )
	List<Object[]> countBySorteioId(Long idLoteria);

}
