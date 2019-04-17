package br.com.alura.alurator;

import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflexao.Reflexao;

public class Alurator {

	private String pacoteBase;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}

	public Object executa(String url) {
		// TODO - processa a requisicao executando o metodo
		// da classe em questao

		Request request = new Request(url);
		String nomeControle = request.GetNomeControle();
		String nomeMetodo = request.GetNomeMetodo();

		Object retorno = new Reflexao()
                .refleteClasse ( pacoteBase + '.' +  nomeControle )
                .criaInstancia()
                .getMetodo(nomeMetodo)
                .invoca();

		System.out.println(retorno);

		return retorno;

	}
}
