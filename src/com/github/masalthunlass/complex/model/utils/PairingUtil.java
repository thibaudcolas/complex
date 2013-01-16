package com.github.masalthunlass.complex.model.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import com.github.masalthunlass.complex.model.enums.DataEnum;
import com.github.masalthunlass.complex.model.enums.SourcesEnum;

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

	/**
	 * Retourne tous les jeux de données déclarés dans le fichier
	 * sources.properties.
	 * 
	 * @return Un set contenant tous les jeux de données
	 * @throws FileNotFoundException
	 *             Si le fichier de configuration est introuvable
	 * @throws IOException
	 *             Si le fichier de configuration est innaccessible
	 */
	public static Set<DataEnum> getAllAvailableData()
			throws FileNotFoundException, IOException {
		Set<DataEnum> data = new HashSet<DataEnum>();
		Set<Entry<Object, Object>> entries = PropertiesUtil
				.getSourcesProperties();
		for (Entry<Object, Object> entry : entries) {
			data.add(DataEnum.valueOf(getDataNameFromKey(
					entry.getKey().toString()).toUpperCase()));
		}

		return data;
	}

	/**
	 * Retourne tous les sysstèmes de données associés à au moins un jeu de
	 * données déclaré dans le fichier sources.properties.
	 * 
	 * @return Un set contenant tous les systèmes de stockage de données
	 *         associés à au moins un jeu de données
	 * @throws FileNotFoundException
	 *             Si le fichier de configuration n'est pas présent dans le
	 *             répertoire /conf
	 * @throws IOException
	 *             Si le fichier de configuration n'est pas accessible
	 */
	public static Set<SourcesEnum> getAllAvailableSources()
			throws FileNotFoundException, IOException {
		Set<SourcesEnum> sources = new HashSet<SourcesEnum>();
		Set<Entry<Object, Object>> entries = PropertiesUtil
				.getSourcesProperties();
		for (Entry<Object, Object> entry : entries) {
			String[] values = entry.getValue().toString().split(",");
			for (String value : values) {
				sources.add(SourcesEnum.valueOf(value.toUpperCase()));
			}
		}

		return sources;
	}

	/**
	 * À partir d'une clé du fichier de configuration sources.properties,
	 * retourne la source (premier terme avant le premier '.' de la clé)
	 * 
	 * @param key
	 *            La clé
	 * @return Le nom de la source de données (sans modification de la casse)
	 */
	private static String getDataNameFromKey(String key) {
		String[] tokens = key.split("\\.");
		if (tokens.length > 0) {
			return tokens[0];
		}

		return null;
	}
}
