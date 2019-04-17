package br.com.alura.alurator.protocolo;

public class Request {

	private String nomeControle;

	public Request(String url) {

		String[] partesUrl = url.replaceFirst("/", "").split("/");
		this.nomeControle = Character.toUpperCase(partesUrl[0].charAt(0)) + partesUrl[0].substring(1) + "Controller";

	}

	public String GetNomeControle() {
		return this.nomeControle;
	}
}
