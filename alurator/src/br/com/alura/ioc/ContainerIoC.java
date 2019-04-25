package br.com.alura.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerIoC {

	private Map<Class<?>, Class<?>> mapaDeTipos = new HashMap<>();

	public Object getInstancia(Class<?> tipoFonte) {

		Class<?> tipoDestino = mapaDeTipos.get(tipoFonte);

		if (tipoDestino != null) {

			return getInstancia(tipoDestino);
		}

		Stream<Constructor<?>> declaredConstructor = Stream.of(tipoFonte.getDeclaredConstructors());

		Optional<Constructor<?>> defaultConstructor = declaredConstructor.filter(c -> c.getParameterCount() == 0)
				.findFirst();

		try {

			if (defaultConstructor.isPresent()) {

				return defaultConstructor.get().newInstance();

			} else {

				Constructor<?> constructor = tipoFonte.getDeclaredConstructors()[0];
				List<Object> params = new ArrayList<>();

				for (Parameter p : constructor.getParameters()) {

					Class<?> type = p.getType();
					params.add(getInstancia(type));

				}

				return constructor.newInstance(params.toArray());

			}

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("");
		}

	}

	public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
		/*
		 * boolean compativel = verificaCompatibilidade(tipoFonte, tipoDestino);
		 * 
		 * if (!compativel) { throw new ClassCastException("Não foi possível resolver "
		 * + tipoFonte.getName() + " e " + tipoDestino.getName()); }
		 */

		mapaDeTipos.put(tipoFonte, tipoDestino);

	}

	private boolean verificaCompatibilidade(Class<?> tipoFonte, Class<?> tipoDestino) {

		/*
		 * no braço boolean compativel;
		 * 
		 * if (tipoFonte.isInterface()) { compativel =
		 * Stream.of(tipoDestino.getInterfaces()).anyMatch(interfaceImpl ->
		 * interfaceImpl.equals(tipoFonte)); } else { compativel =
		 * tipoDestino.getSuperclass().equals(tipoFonte) ||
		 * tipoDestino.equals(tipoFonte); }
		 * 
		 * return compativel;
		 */

		return tipoFonte.isAssignableFrom(tipoDestino); // api reflection
	}

}
