package br.com.alura.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerIoC {

	public Object getInstancia(Class<?> tipoFonte) {

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

}
