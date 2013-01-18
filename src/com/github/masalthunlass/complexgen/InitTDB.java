package com.github.masalthunlass.complexgen;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import com.github.masalthunlass.complex.model.enums.DataEnum;
import com.github.masalthunlass.complex.model.utils.ResourcesUtil;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;

public class InitTDB {

	private static final String BASE_PATH = "/tdb";
	private static final String WORKING_DIRECTORY = ".";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("INITIALISATION DES FICHIERS TDB");
		System.out.println("Suppression des répertoires TDB");
		suppressDirectories();
		System.out.println("Création des répertoires TDB");
		createDirectories();
		System.out.println("Importation des données");
		importData();
	}

	private static void suppressDirectories() {
		DataEnum[] data = DataEnum.values();
		for (DataEnum d : data) {
			String subdirname = d.toString().toLowerCase();
			File folder = new File(WORKING_DIRECTORY + BASE_PATH + "/"
					+ subdirname);
			if (folder.exists()) {
				for (File file : folder.listFiles()) {
					file.delete();
				}
				folder.delete();
			}
		}
	}

	private static void createDirectories() {
		DataEnum[] data = DataEnum.values();
		for (DataEnum d : data) {
			String subdirname = d.toString().toLowerCase();
			File folder = new File(WORKING_DIRECTORY + BASE_PATH + "/"
					+ subdirname);
			folder.mkdir();
		}
	}

	private static void importData() {
		HashMap<String, String> file_path = ResourcesUtil.getFilePath();
		Set<String> keys = file_path.keySet();
		for (String key : keys) {
			System.out.println("\tImportation données " + key);
			String subdirname = key;
			String rdfpath = file_path.get(key);

			Dataset ds = TDBFactory.createDataset(WORKING_DIRECTORY + BASE_PATH
					+ "/" + subdirname);
			Model model = ds.getDefaultModel();
			FileManager.get().readModel(model, WORKING_DIRECTORY + rdfpath);
			System.out.println("\t ->" + model.size() + " triplets insérés");
		}
	}
}
