package com.github.masalthunlass.complex.model.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.github.masalthunlass.complex.model.enums.DataEnum;

/**
 * Class permettant la gestion des fichiers de properties
 * 
 * @author thibaut
 * 
 */
public class PropertiesUtil {
	public static Logger logger = (Logger) Logger
			.getLogger(PropertiesUtil.class);

	/** Properties filenames */
	private static String SOURCES_PATH = "sources.properties";
	private static String SDB_GEONAMES_PATH = "sdb-geonames.properties";
	private static String SDB_ISF_PATH = "sdb-geonames.properties";
	private static String SDB_PASSIM_PATH = "sdb-passim.properties";
	private static String SDB_INSEEPOP_PATH = "sdb-inseepop.properties";
	private static String SDB_INSEECOG_PATH = "sdb-inseecog.properties";
	private static String SDB_MONUMENTS_PATH = "sdb-monuments.properties";
	private static String TDB_PATH = "tdb.properties";

	/** Applications Path & values */
	private static String projectPath = System.getProperty("user.dir");
	private static String propertiesPath = projectPath + "/conf";

	/** properties values */
	private static Set<Entry<Object, Object>> sources_values = null;
	private static Set<Entry<Object, Object>> sdb_geonames_values = null;
	private static Set<Entry<Object, Object>> sdb_isf_values = null;
	private static Set<Entry<Object, Object>> sdb_passim_values = null;
	private static Set<Entry<Object, Object>> sdb_inseepop_values = null;
	private static Set<Entry<Object, Object>> sdb_inseecog_values = null;
	private static Set<Entry<Object, Object>> sdb_monuments_values = null;
	private static Set<Entry<Object, Object>> tdb_values = null;

	public static String getPropertiesPath() {
		return propertiesPath;
	}

	public static String getProjectPath() {
		return projectPath;
	}

	/**
	 * Assure l'accès aux valeurs des paramètres définies dans le fichier de
	 * configuration des sources.
	 * 
	 * @param key
	 *            Le nom de la clé
	 * @return La valeur si la clé existe, null sinon
	 * @throws FileNotFoundException
	 *             Si le fichier n'existe pas
	 * @throws IOException
	 *             Si le fichier n'est pas accessible
	 */
	public static String getSourcesProperty(String key)
			throws FileNotFoundException, IOException {
		Iterator<Entry<Object, Object>> it = getSourcesProperties().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
			String entry_key = (String) entry.getKey();
			String entry_value = (String) entry.getValue();

			if (entry_key.equalsIgnoreCase(key))
				return entry_value;

		}
		return null;
	}

	/**
	 * Assure l'accès aux valeurs des paramètres définies dans le fichier de
	 * configuration de tdb.
	 * 
	 * @param key
	 *            Le nom de la clé
	 * @return La valeur si la clé existe, null sinon
	 * @throws FileNotFoundException
	 *             Si le fichier n'existe pas
	 * @throws IOException
	 *             Si le fichier n'est pas accessible
	 */
	public static String getTdbProperty(String key)
			throws FileNotFoundException, IOException {
		Iterator<Entry<Object, Object>> it = getTdbProperties().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
			String entry_key = (String) entry.getKey();
			String entry_value = (String) entry.getValue();

			if (entry_key.equalsIgnoreCase(key))
				return entry_value;

		}
		return null;
	}

	/**
	 * Assure l'accès aux valeurs des paramètres définies dans le fichier de
	 * configuration du système de stockage.
	 * 
	 * @param data
	 *            Le système de stockage concerné
	 * @param key
	 *            Le nom de la clé
	 * @return La valeur si la clé existe, null sinon
	 * @throws FileNotFoundException
	 *             Si le fichier n'existe pas
	 * @throws IOException
	 *             Si le fichier n'est pas accessible
	 */
	public static String getDataProperty(DataEnum data, String key)
			throws FileNotFoundException, IOException {
		Iterator<Entry<Object, Object>> it = getDataProperties(data).iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
			String entry_key = (String) entry.getKey();
			String entry_value = (String) entry.getValue();

			if (entry_key.equalsIgnoreCase(key))
				return entry_value;

		}
		return null;
	}

	/**
	 * Permet de charger les clés/valeurs définies dans le fichier de
	 * configuration des sources. Cette méthode respecte les principes du
	 * singleton.
	 * 
	 * @return Les clés/valeurs sous la forme d'un set d'entry
	 * @throws FileNotFoundException
	 *             Si le fichier de configuration n'existe pas
	 * @throws IOException
	 *             Si le fichier de configuration n'est pas accessible
	 */
	public static Set<Entry<Object, Object>> getSourcesProperties()
			throws FileNotFoundException, IOException {
		if (sources_values == null)
			loadProperties();
		return sources_values;
	}

	/**
	 * Permet de charger les clés/valeurs définies dans le fichier de
	 * configuration de tdb. Cette méthode respecte les principes du singleton.
	 * 
	 * @return Les clés/valeurs sous la forme d'un set d'entry
	 * @throws FileNotFoundException
	 *             Si le fichier de configuration n'existe pas
	 * @throws IOException
	 *             Si le fichier de configuration n'est pas accessible
	 */
	public static Set<Entry<Object, Object>> getTdbProperties()
			throws FileNotFoundException, IOException {
		if (tdb_values == null)
			loadProperties();
		return tdb_values;
	}

	/**
	 * Permet de charger les clés/valeurs définies dans le fichier de
	 * configuration du systeme de stockage définit. Cette méthode respecte les
	 * principes du singleton.
	 * 
	 * @param data
	 *            Le système de stockage concerné
	 * @return Les clés/valeurs sous la forme d'un set d'entry
	 * @throws FileNotFoundException
	 *             Si le fichier de configuration n'existe pas
	 * @throws IOException
	 *             Si le fichier de configuration n'est pas accessible
	 */
	public static Set<Entry<Object, Object>> getDataProperties(DataEnum data)
			throws FileNotFoundException, IOException {
		switch (data) {
		case GEONAMES:
			if (sdb_geonames_values == null)
				loadProperties();
			return sdb_geonames_values;
		case INSEECOG:
			if (sdb_inseecog_values == null)
				loadProperties();
			return sdb_inseecog_values;
		case INSEEPOP:
			if (sdb_inseepop_values == null)
				loadProperties();
			return sdb_inseepop_values;
		case ISF:
			if (sdb_isf_values == null)
				loadProperties();
			return sdb_isf_values;
		case MONUMENTS:
			if (sdb_monuments_values == null)
				loadProperties();
			return sdb_monuments_values;
		case PASSIM:
			if (sdb_passim_values == null)
				loadProperties();
			return sdb_passim_values;
		default:
			return null;
		}
	}

	/**
	 * Charge le fichier de configuration (Appellé uniquement si celui-ci n'a
	 * pas été chargé auparavant).
	 * 
	 * @throws FileNotFoundException
	 *             Si le fichier de configuration n'existe pas
	 * @throws IOException
	 *             Si le fichier de configuration n'est pas accessible
	 */
	private static void loadProperties() throws FileNotFoundException,
			IOException {
		Properties properties = new Properties();
		// Loading sources properties
		properties
				.load(new FileInputStream(propertiesPath + "/" + SOURCES_PATH));
		sources_values = properties.entrySet();
		properties = new Properties();

		properties.load(new FileInputStream(propertiesPath + "/"
				+ SDB_GEONAMES_PATH));
		sdb_geonames_values = properties.entrySet();
		properties = new Properties();
		properties.load(new FileInputStream(propertiesPath + "/"
				+ SDB_INSEECOG_PATH));
		sdb_inseecog_values = properties.entrySet();
		properties = new Properties();
		properties.load(new FileInputStream(propertiesPath + "/"
				+ SDB_INSEEPOP_PATH));
		sdb_inseepop_values = properties.entrySet();
		properties = new Properties();
		properties
				.load(new FileInputStream(propertiesPath + "/" + SDB_ISF_PATH));
		sdb_isf_values = properties.entrySet();
		properties = new Properties();
		properties.load(new FileInputStream(propertiesPath + "/"
				+ SDB_MONUMENTS_PATH));
		sdb_monuments_values = properties.entrySet();
		properties = new Properties();
		properties.load(new FileInputStream(propertiesPath + "/"
				+ SDB_PASSIM_PATH));
		sdb_passim_values = properties.entrySet();

		properties = new Properties();
		properties.load(new FileInputStream(propertiesPath + "/" + TDB_PATH));
		tdb_values = properties.entrySet();

	}

}