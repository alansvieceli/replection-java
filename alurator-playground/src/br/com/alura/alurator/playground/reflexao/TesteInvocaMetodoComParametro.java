package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TesteInvocaMetodoComParametro {

	public static void main(String[] args) throws Exception  {
		
		Class<?> controleClasse = Class.forName("br.com.alura.alurator.playground.controle.Controle");

		Constructor<?> construtorPadrao = controleClasse.getDeclaredConstructor();

		Object controle = construtorPadrao.newInstance();

		Method m = controleClasse.getDeclaredMethod("metodoControle2", String.class);
		
		Object retorno = m.invoke(controle, "Pintassilgo do Agreste");
		
		System.out.println("------------------");
		
		Method m2 = controleClasse.getDeclaredMethod("metodoControle2", String.class, Integer.class);

		Object retorno2 = m2.invoke(controle, "Pintassilgo do Agreste", 1);

		System.out.println("------------------");

		System.out.println(retorno);
		System.out.println(retorno2);

	}

}
