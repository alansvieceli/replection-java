package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.controle.Controle;

public class TesteInstanciaObjeto {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<Controle> controleClasse1 = Controle.class;

		Controle controle = new Controle();

		Class<? extends Controle> controleClasse2 = controle.getClass();

		Class<?> controleClasse3 = Class.forName("br.com.alura.alurator.playground.controle.Controle");

		Object object1 = controleClasse1.newInstance();
		Object object2 = controleClasse2.newInstance();
		Object object3 = controleClasse3.newInstance();
		
		System.out.println(object1 instanceof Controle);
		System.out.println(object2 instanceof Controle);
		System.out.println(object3 instanceof Controle);

	}

}
