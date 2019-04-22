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
		 * Para resolvermos esse tipo de problema, precisar�amos utilizar alguma biblioteca que nos possibilitasse manipular nossos par�metros com seus nomes originais. Uma biblioteca 
		 * bem famosa, bastante utilizada por quem trabalha com Reflection, � a chamada Paranamer. No entanto, o Java permite, a partir da vers�o 8, que recuperemos o 
		 * nome dos nossos par�metros usando a pr�pria API de Reflection.
		 *
		 * Para isso, clicaremos com o bot�o direito no projeto estique-api e em seguida em "Propriedades". Na se��o "Java Compiler", selecionaremos a op��o "Store information 
		 * about method parameters (usable via reflection)", que indica que precisamos guardar as informa��es relativas aos par�metros dos m�todos. Por padr�o, essa op��o vem desmarcada.
		 * 
		 * 
		 */
		
		// 1) Pegar todos os m�todos da classe.
		// 2) Filtrar todos os m�todos de modo que:
		//   2.1) Tenham o mesmo nome informado pelo usu�rio;
		//   2.2) Tenham a mesma quantidade de par�metros passados na URL;
		//   2.3) E que cada um dos par�metros tenham os mesmos nomes e tipos iguais
		// aos passados na URL.
		// 3) Lan�ar uma RuntimeException caso nenhum m�todo seja encontrado.
		
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
                					.orElseThrow(() -> new RuntimeException("M�todo n�o encontrado!"));


			return new ManipuladorMetodo(instancia, metodoSelecionado, params);


	}
}
