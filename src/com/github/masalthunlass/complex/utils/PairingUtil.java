package com.github.masalthunlass.complex.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.masalthunlass.complex.enums.DataEnum;
import com.github.masalthunlass.complex.enums.SourcesEnum;

/**
 * Classe utilitaire permettant de faciliter la gestion des couplages.
 * 
 * @author thibaut
 * 
 */
public class PairingUtil {
	/**
	 * Retourne vrai si le pairing est possible (déclaré dans le fichier de
	 * conf)
	 * 
	 * @param data
	 *            Le jeu de données
	 * @param source
	 *            Le système de gestion de données
	 * @return Vrai si le pairing est possible, faux sinon
	 * @throws FileNotFoundException
	 *             Si le fichier de conf n'existe pas
	 * @throws IOException
	 *             Si le fichier de conf n'est pas accessible
	 */
	public static Boolean verify(DataEnum data, SourcesEnum source)
			throws FileNotFoundException, IOException {
		System.out.println("verifying");
		Boolean verified = false;

		String available = PropertiesUtil.getSourcesProperty(data.toString()
				.toLowerCase() + ".availablein");
		if (available != null) {
			String[] tokens = available.split(",");
			for (int i = 0; i < tokens.length; ++i) {
				if (source.toString().equalsIgnoreCase(tokens[i]))
					verified = true;
			}
		}
		return verified;
	}
}
