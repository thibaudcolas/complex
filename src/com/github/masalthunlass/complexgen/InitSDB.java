package com.github.masalthunlass.complexgen;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import com.github.masalthunlass.complex.model.enums.DataEnum;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;

public class InitSDB {

	private static final String WORKING_DIRECTORY = ".";
	private static final String DB_USER = "user";
	private static final String DB_PASSWD = "passwd";

	private static HashMap<String, String> file_path;
	private static HashMap<String, String> config_path;

	private static void initPathFiles() {
		file_path = new HashMap<String, String>();
		// file_path.put("inseepop", "/resources/data/rdf/inseepop.rdf");
		// file_path.put("inseecog", "/resources/data/rdf/inseecog.rdf");
		file_path.put("geonames", "/resources/data/rdf/geonames.rdf");
		file_path.put("passim", "/resources/data/rdf/passim.rdf");
		// file_path.put("isf", "/resources/data/rdf/isf.rdf");
		// file_path.put("monuments", "/resources/data/rdf/monuments.rdf");

		config_path = new HashMap<String, String>();
		// file_path.put("inseepop", "/resources/data/rdf/inseepop.rdf");
		// file_path.put("inseecog", "/resources/data/rdf/inseecog.rdf");
		config_path.put("geonames", "/conf/sdb-geonames.ttl");
		config_path.put("passim", "/conf/sdb-passim.ttl");
		// file_path.put("isf", "/resources/data/rdf/isf.rdf");
		// file_path.put("monuments", "/resources/data/rdf/monuments.rdf");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("INITIALISATION DES FICHIERS SDB");
		initPathFiles();
		System.out.println("Nettoyage des bases de données");
		clearBDD();
		System.out.println("Initialisation des bases de données");
		initBDD();
		System.out.println("Importation des données");
		importData();
	}

	private static void clearBDD() {
		// TODO
	}

	private static void initBDD() {
		// TODO
	}

	private static void importData() {
		Set<String> keys = file_path.keySet();
		for (String key : keys) {
			// TODO importData :)
		}
	}
}
