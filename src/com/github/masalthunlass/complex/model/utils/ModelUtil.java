package com.github.masalthunlass.complex.model.utils;

import com.github.masalthunlass.complex.exceptions.ModelException;
import com.github.masalthunlass.complex.model.enums.DataEnum;
import com.github.masalthunlass.complex.model.enums.SourcesEnum;
import com.hp.hpl.jena.rdf.model.Model;

public class ModelUtil {

	/**
	 * Génère un modèle Jena en fonction d'un jeu de données et d'un système de
	 * stockage.
	 * 
	 * @param data
	 *            Le jeu de données
	 * @param source
	 *            Le système de stockage
	 * @return Le modèle Jena correspondant
	 * @throws ModelException
	 *             Si la génération s'est mal passée
	 */
	public static Model generateModel(DataEnum data, SourcesEnum source)
			throws ModelException {
		switch (source) {
		case TDB:
			return getTDBModel(data);
		case SDB:
			return getSDBModel(data);
		case D2RQ:
			return getD2RQModel(data);
		default:
			throw new ModelException("Oops, something goes wrong...");
		}
	}

	private static Model getTDBModel(DataEnum data) {
		// TODO retourner le modèle TDB correspondant au jeu de données
		return null;
	}

	private static Model getSDBModel(DataEnum data) {
		// TODO retourner le modèle SDB correspondant au jeu de données
		return null;
	}

	private static Model getD2RQModel(DataEnum data) {
		// TODO retourner le modèle D2RQ correspondant au jeu de données
		return null;
	}
}
