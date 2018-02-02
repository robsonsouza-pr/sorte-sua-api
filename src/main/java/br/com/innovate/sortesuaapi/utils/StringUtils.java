package br.com.innovate.sortesuaapi.utils;

public class StringUtils {

	public static boolean isNumeric(String item) {
		
		return item.trim().matches("\\d+");
	}

}
