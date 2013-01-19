package com.github.masalthunlass.complex.model.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.masalthunlass.complex.controller.AppServlet;
import com.github.masalthunlass.complex.exceptions.ModelException;
import com.github.masalthunlass.complex.model.enums.DataEnum;
import com.github.masalthunlass.complex.model.enums.SourcesEnum;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.store.DatasetStore;
import com.hp.hpl.jena.tdb.TDBFactory;

import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

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
	 * @throws IOException
	 *             Si le fichier de configuration n'existe pas
	 * @throws FileNotFoundException
	 *             Si le fichier de configuration n'est pas accessible
	 */
	public static Model generateModel(DataEnum data, SourcesEnum source)
			throws ModelException, FileNotFoundException, IOException {
		switch (source) {
		case TDB:
			return getTDBModel(data);
		case SDB:
			return getSDBModel(data);
		case D2RQ:
			return getD2RQModel(data);
		case MEMORY:
			return getMemoryModel(data);
		default:
			throw new ModelException("Oops, something goes wrong...");
		}
	}

	private static Model getTDBModel(DataEnum data)
			throws FileNotFoundException, IOException {
		String tdbPath = PropertiesUtil.getProjectPath()
				+ PropertiesUtil.getTdbProperty("tdb_path");
		if (!tdbPath.endsWith("/"))
			tdbPath += "/";

		tdbPath += data.toString().toLowerCase() + "/";
		Dataset ds = TDBFactory.createDataset(tdbPath);
		System.out.println("Creating " + data + " TDB Model width path "
				+ tdbPath);
		Model model = ds.getDefaultModel();
		return model; // TODO resolve null pointer exception
						// here...
	}

	private static Model getSDBModel(DataEnum data)
			throws FileNotFoundException, IOException {
		System.setProperty("jena.db.user", AppServlet.getSdbUser());
		System.setProperty("jena.db.password", AppServlet.getSdbPassword());
		String configPath = PropertiesUtil.getDataProperty(data, "sdb_config");
		String path = PropertiesUtil.getPropertiesPath() + "/" + configPath;
		System.out.println("Creating " + data + " SDB Model at " + path);
		Store store = SDBFactory.connectStore(path);
		Dataset ds = DatasetStore.create(store);
		return ds.getDefaultModel(); // TODO Vérifier ça :)
	}

	private static Model getD2RQModel(DataEnum data)
			throws FileNotFoundException, IOException {
		String configPath = PropertiesUtil.getDataProperty(data, "d2rq_config");
		System.out.println("Creating " + data + " D2RQ model from file:"
				+ "file:" + PropertiesUtil.getPropertiesPath() + "/"
				+ configPath);
		return new ModelD2RQ("file:" + PropertiesUtil.getPropertiesPath() + "/"
				+ configPath);
	}

	private static Model getMemoryModel(DataEnum data) {
		Model model = ModelFactory.createDefaultModel(); // createDefaultModel
															// crée par défault
															// un model
															// memory-based
		String data_path = ResourcesUtil.getProjectPath()
				+ ResourcesUtil.getDataPath(data);
		System.out.println("Creating " + data
				+ " in memory model from file: " + ResourcesUtil.getProjectPath()
				+ ResourcesUtil.getDataPath(data));
		model.read("file://" + data_path);
		return model;
	}
}
