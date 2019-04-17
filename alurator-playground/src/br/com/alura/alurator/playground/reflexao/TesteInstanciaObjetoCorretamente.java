package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import br.com.alura.alurator.playground.controle.Controle;
import br.com.alura.alurator.playground.controle.SubControle;

public class TesteInstanciaObjetoCorretamente {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<SubControle> subControleClasse1 = SubControle.class;

		Class<?> subControleClasse2 = Class.forName("br.com.alura.alurator.playground.controle.SubControle");

		Class<?> controleClasse1 = Class.forName("br.com.alura.alurator.playground.controle.Controle");
		
		
		Constructor<SubControle> constructor1 = subControleClasse1.getConstructor(); //só public
		
		Constructor<SubControle> constructor2 = subControleClasse1.getDeclaredConstructor(String.class); //tudo
		
		System.out.println(constructor1);
		System.out.println(constructor2);
		
		//Constructor<SubControle> constructor1 = subControleClasse1.getConstructor();
		
		Object object1 = constructor1.newInstance();
		constructor2.setAccessible(true); //acessar construtor privado
		Object object2 = constructor2.newInstance("Teste");
		
		System.out.println(object1 instanceof Controle);
		System.out.println(object2 instanceof Controle);

	}

}
