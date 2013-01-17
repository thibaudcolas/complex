package com.github.masalthunlass.complexgen;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.github.masalthunlass.complex.model.enums.DataEnum;

public class InitTDB {

	private static final String BASE_PATH = "/tdb";
	private static final String WORKING_DIRECTORY = ".";

	private static HashMap<String, String> file_path;

	private static void initPathFiles() {
		// file_path.put("inseepop", "/resources/data/rdf/inseepop.rdf");
		// file_path.put("inseecog", "/resources/data/rdf/inseecog.rdf");
		// file_path.put("geonames", "/resources/data/rdf/geonames.rdf");
		file_path.put("passim", "/resources/data/rdf/passim.rdf");
		// file_path.put("isf", "/resources/data/rdf/isf.rdf");
		// file_path.put("monuments", "/resources/data/rdf/monuments.rdf");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initPathFiles();
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
			if (folder.exists())
				folder.delete();
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
		Set<String> keys = file_path.keySet();
		for (String key : keys) {
			String subdirname = key;
			String rdfpath = file_path.get(key);
		}
	}
}
