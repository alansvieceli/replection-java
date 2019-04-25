package br.com.alura.alurator;

import java.util.Map;

import br.com.alura.alurator.conversor.ConversorXML;
import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflexao.ManipuladorObjeto;
import br.com.alura.alurator.reflexao.Reflexao;
import br.com.alura.ioc.ContainerIoC;

public class Alurator {

	private String pacoteBase;
	private ContainerIoC container;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
		this.container = new ContainerIoC();
	}

	public Object executa(String url) {
		// TODO - processa a requisicao executando o metodo
		// da classe em questao

		Request request = new Request(url);
		String nomeControle = request.GetNomeControle();
		String nomeMetodo = request.GetNomeMetodo();
		Map<String, Object> params = request.getQueryParams();

		Class<?> classeControle = new Reflexao().getClasse(pacoteBase + '.' + nomeControle);
		
		Object instanciaControle = container.getInstancia(classeControle);
		
		Object objeto = new ManipuladorObjeto(instanciaControle)
				.getMetodo(nomeMetodo, params)
				.comTratamentoDeExcecao((metodo, ex) -> {
					System.out.println("Erro no método " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName() + ".\n\n");
					throw new RuntimeException("Erro no método!");
				})
				.invoca();

		String retorno = new ConversorXML().converte(objeto);

		return retorno;

	}

	public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
		
		container.registra(tipoFonte, tipoDestino);	
		
		
	}
}
