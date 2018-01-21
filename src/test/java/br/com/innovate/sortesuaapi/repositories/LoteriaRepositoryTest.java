package br.com.innovate.sortesuaapi.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.innovate.sortesuaapi.models.Loteria;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LoteriaRepositoryTest {
	
	@Autowired
	private LoteriaRepository loteriaRepository;
	
	private final String NOME = "Lotofacil";
	
	@Before
	public void setUp() throws Exception {
		Loteria loteria = new Loteria();
		loteria.setNome(NOME);
		loteria.setDigitos(25);
		loteria.setDescricao("A loteria com mais chances de vencer");
		
		this.loteriaRepository.save(loteria);
	}
	
	@After
    public final void tearDown() { 
		this.loteriaRepository.deleteAll();
	}
	
	@Test
	public void buscarPorNome() {
		Loteria  loteria = loteriaRepository.findByNome(NOME);
		assertEquals(NOME, loteria.getNome());
	}

}
