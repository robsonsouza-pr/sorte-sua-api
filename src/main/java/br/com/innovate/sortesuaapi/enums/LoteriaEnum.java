package br.com.innovate.sortesuaapi.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import br.com.innovate.sortesuaapi.models.Loteria;

public enum LoteriaEnum {

	LOTOFACIL(1L), MEGASENA(2L), LOTOMANIA(3L), QUINA(4L);

	private static final Map<Long, LoteriaEnum> LOOKUP = new HashMap<>();

	static {
		for (LoteriaEnum e : EnumSet.allOf(LoteriaEnum.class)) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private LoteriaEnum(Long id) {
		this.id = id;
	}

	public static LoteriaEnum valueOf(Long id) {
		return LOOKUP.get(id);
	}

	public static Loteria getEntity(String loteria) {
		Loteria retorno = new Loteria();
		retorno.setId(valueOf(loteria).getId());
		return retorno;
	}
}
