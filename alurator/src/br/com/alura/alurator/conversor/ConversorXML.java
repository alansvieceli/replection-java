package br.com.alura.alurator.conversor;

import java.lang.reflect.Field;
import java.util.Collection;

import br.com.alura.alurator.conversor.anotacao.NomeTagXML;

public class ConversorXML {

	public String converte(Object objeto) {

		try {

			Class<?> classObjecto = objeto.getClass();

			StringBuilder xmlBuilder = new StringBuilder();

			if (objeto instanceof Collection) {
				Collection<?> colecao = (Collection<?>) objeto;

				xmlBuilder.append("<lista>");

				for (Object o : colecao) {
					String xml = converte(o);
					xmlBuilder.append(xml);
				}

				xmlBuilder.append("</lista>");

			} else {

				String nomeClasse = classObjecto.getName();
				String tagClasse = "";
				NomeTagXML declaredAnnotation = classObjecto.getDeclaredAnnotation(NomeTagXML.class);
				if (declaredAnnotation == null) {
					tagClasse = nomeClasse;
				} else {
					tagClasse = declaredAnnotation.value();				}
				
				//NomeTagXml anotacao = nomeClasse.getDeclaredAnnotation(NomeTagXml.class)

				xmlBuilder.append("<" + tagClasse + ">");

				for (Field atributo : classObjecto.getDeclaredFields()) {
					atributo.setAccessible(true);

					String nomeAtributo = atributo.getName();

					Object valorAtributo = atributo.get(objeto);

					xmlBuilder.append("<" + nomeAtributo + ">");
					xmlBuilder.append(valorAtributo);
					xmlBuilder.append("</" + nomeAtributo + ">");

				}

				xmlBuilder.append("</" + tagClasse + ">");
			}
			
			return xmlBuilder.toString();

		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro na geração do XML!");
		}
	}

}
