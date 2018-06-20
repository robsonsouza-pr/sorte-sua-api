package br.com.innovate.sortesuaapi.utils;

public class Teste {
	public static void main(String args[]) {
		SenhaUtils senhaUtils = new SenhaUtils();
		System.out.println(senhaUtils.isValid("123456", "$2a$10$RSfT/kVr64PyzMb2b.ZE6OwooWj4unX.1VPyfK3yyxLwRzLjAuQ3i"));
	}

}
