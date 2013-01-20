package com.github.masalthunlass.complexgen;

import java.util.HashMap;
import java.util.Set;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;

import com.hp.hpl.jena.util.FileManager;

public class InitSDB {

	private static final String WORKING_DIRECTORY = ".";
	//TODO Create a conf/sdb.properties file with those values inside.
	//TODO Or use the ones defined in web.xml (but requires both projects to stay together).
//	private static final String DB_USER = System.getProperty("sdb.user");
//	private static final String DB_USER = System.getProperty("sdb.password");
	private static final String DB_USER = "sdb";
	private static final String DB_PASSWD = "password";

	private static HashMap<String, String> file_path;
	private static HashMap<String, String> config_path;
	private static HashMap<String, Store> stores = new HashMap<String, Store>();

	private static void initPathFiles() {
		file_path = new HashMap<String, String>();
		file_path.put("inseepop", "/resources/data/rdf/inseepop.rdf");
		file_path.put("inseecog", "/resources/data/rdf/inseecog.rdf");
		file_path.put("geonames", "/resources/data/rdf/geonames.rdf");
		file_path.put("passim", "/resources/data/rdf/passim.rdf");
		file_path.put("isf", "/resources/data/rdf/isf.rdf");
		file_path.put("monuments", "/resources/data/rdf/monuments.rdf");

		config_path = new HashMap<String, String>();
		 config_path.put("inseepop", "/conf/sdb-inseepop.ttl");
		 config_path.put("inseecog", "/conf/sdb-inseecog.ttl");
		config_path.put("geonames", "/conf/sdb-geonames.ttl");
		config_path.put("passim", "/conf/sdb-passim.ttl");
		 config_path.put("isf", "/conf/sdb-isf.ttl");
		 config_path.put("monuments", "/conf/sdb-monuments.ttl");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("INITIALISATION DES FICHIERS SDB");
		initPathFiles();
		System.out.println("Nettoyage des bases de données");
		clearBDD();
		System.out.println("Initialisation des stores");
		initBDD();
		System.out.println("Importation des données");
		importData();

	}

	private static void clearBDD() {
		if (!stores.isEmpty()) {
			Set<String> keys = stores.keySet();
			for (String key : keys) {
				Store store = stores.get(key);
				store.getTableFormatter().truncate();
			}
		}
	}

	private static void initBDD() {
		System.setProperty("jena.db.user", DB_USER);
		System.setProperty("jena.db.password", DB_PASSWD);
		Set<String> keys = config_path.keySet();
		for (String key : keys) {
			Store store = SDBFactory.connectStore(WORKING_DIRECTORY
					+ config_path.get(key));
			store.getTableFormatter().create();
			stores.put(key, store);
		}
	}

	private static void importData() {
		Set<String> keys = stores.keySet();
		for (String key : keys) {
			Store store = stores.get(key);
			System.out.println("\tImportation données " + key);
			String rdfpath = file_path.get(key);
			Dataset ds = SDBFactory.connectDataset(store);
			Model model = ds.getDefaultModel();
			FileManager.get().readModel(model, WORKING_DIRECTORY + rdfpath);
			System.out.println("\t ->" + model.size() + " triplets insérés");
		}
	}
}
