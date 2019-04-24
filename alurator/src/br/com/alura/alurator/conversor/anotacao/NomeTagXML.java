package br.com.alura.alurator.conversor.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface NomeTagXML {

	public String value();
	
	//public String valor(); // NomeTagXML(valor="xxx")

}