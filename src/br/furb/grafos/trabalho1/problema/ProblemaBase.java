package br.furb.grafos.trabalho1.problema;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ProblemaBase {

	protected List<String> getLinhasProblema(String problema) {
		return Arrays.stream(problema.split("\n")) //
				.map(String::trim) //
				.collect(Collectors.toList());
	}
}
