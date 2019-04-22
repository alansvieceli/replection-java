package br.com.alura.alurator.reflexao;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class ManipuladorObjeto {

	private Object instancia;

	public ManipuladorObjeto(Object instancia) {
		this.instancia = instancia;

	}

	public ManipuladorMetodo getMetodo(String nomeMetodo, Map<String, Object> params) {
		
		/**
		 * Para resolvermos esse tipo de problema, precisaríamos utilizar alguma biblioteca que nos possibilitasse manipular nossos parâmetros com seus nomes originais. Uma biblioteca 
		 * bem famosa, bastante utilizada por quem trabalha com Reflection, é a chamada Paranamer. No entanto, o Java permite, a partir da versão 8, que recuperemos o 
		 * nome dos nossos parâmetros usando a própria API de Reflection.
		 *
		 * Para isso, clicaremos com o botão direito no projeto estique-api e em seguida em "Propriedades". Na seção "Java Compiler", selecionaremos a opção "Store information 
		 * about method parameters (usable via reflection)", que indica que precisamos guardar as informações relativas aos parâmetros dos métodos. Por padrão, essa opção vem desmarcada.
		 * 
		 * 
		 */
		
		// 1) Pegar todos os métodos da classe.
		// 2) Filtrar todos os métodos de modo que:
		//   2.1) Tenham o mesmo nome informado pelo usuário;
		//   2.2) Tenham a mesma quantidade de parâmetros passados na URL;
		//   2.3) E que cada um dos parâmetros tenham os mesmos nomes e tipos iguais
		// aos passados na URL.
		// 3) Lançar uma RuntimeException caso nenhum método seja encontrado.
		
		Stream<Method> metodos = Stream.of(instancia.getClass().getDeclaredMethods()); //1.
		Method metodoSelecionado = metodos.filter(metodo -> 
										metodo.getName().equals(nomeMetodo)  //2.1 
									 && metodo.getParameterCount() == params.values().size() //2.2
									 && Stream.of(metodo.getParameters())
                                     		.allMatch(arg -> 
                                     			params.keySet().contains(arg.getName())
                                     		 && params.get(arg.getName()).getClass().equals(arg.getType())
                                     		)
                     				) 
                					.findFirst()
                					.orElseThrow(() -> new RuntimeException("Método não encontrado!"));


			return new ManipuladorMetodo(instancia, metodoSelecionado, params);


	}
}
