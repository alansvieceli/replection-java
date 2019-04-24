package br.com.alan.application;

public class ClassTeste {

	private String abacabb;

	public ClassTeste() {
		this.abacabb = "Default";
	}
	
	public ClassTeste(String str) {
		this.abacabb = str;
	}
	
	public void MetodoPublico() {
		System.out.println("Exectou o Metodo");
		System.out.println(this.abacabb);
	}

}
