package br.com.alan.application;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TesteApp {


	public static void main(String[] args) throws Exception {

		
		String className = "ClassTeste";
		String pacote = "br.com.alan.application";

		Class<?> classe001 = Class.forName(pacote + "." + className);
		
		//buscando contrutor
		Constructor<?> constructor01 = classe001.getConstructor();
		
		Constructor<?> constructor02 = classe001.getDeclaredConstructor();
		
		Object object01 = constructor01.newInstance();
		
		Object object02 = constructor02.newInstance();
		
		if (object01 instanceof ClassTeste) {
			
			Method m = classe001.getDeclaredMethod("MetodoPublico");
			m.invoke(object01);
			
		}


	}

}
