package br.com.alura.alurator.reflexao;

public class ManipuladorClasse {

	private Class<?> classe;

	public ManipuladorClasse(Class<?> classe) {
		this.classe = classe;

	}

	public ManipuladorConstrutor getConstrutorPadrao() {
		try {
			return new ManipuladorConstrutor(this.classe.getConstructor());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public ManipuladorObjeto criaInstancia() {
		Object instancia = getConstrutorPadrao().invoca();
		return new ManipuladorObjeto(instancia);
	}

}
