package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Field;

import br.com.alura.alurator.playground.modelo.Produto;
import br.com.alura.alurator.playground.anotacao.NomeTagXML;

public class TesteManipulaAtributos {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {

		Object produto = new Produto("Produto 1", 20.0, "Marca 1");
		Class<?> classe = produto.getClass();
		
		NomeTagXML declaredAnnotation = classe.getDeclaredAnnotation(NomeTagXML.class);
		
		System.out.println(declaredAnnotation.value());
		

		for (Field atributo : classe.getDeclaredFields()) {
	//	for (Field atributo : classe.getFields()) {
			atributo.setAccessible(true);
		    System.out.println(atributo.getName() + ": " + atributo.get(produto) );
		}

	}

}
