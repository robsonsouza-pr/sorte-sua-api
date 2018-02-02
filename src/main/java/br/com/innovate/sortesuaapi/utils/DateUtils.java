package br.com.innovate.sortesuaapi.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	static DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy"); 
	
	public static String formatarData(Date date) {
		return date == null ? fmt.format(new Date()) :  fmt.format(date);
	}
	
	public static Date converterParaDate(String data) {
		Date date = null;
		try {
			date =  fmt.parse(data);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}
}
