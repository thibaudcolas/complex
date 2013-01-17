package com.github.masalthunlass.complex.model.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

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

	/** Applications Path & values */
	private static String projectPath = System.getProperty("user.dir");
	private static String webAppPath = projectPath + "/complex";
	private static String webInfPath = webAppPath + "/WEB-INF";
	private static String srcPath = projectPath + "/src";
	private static String propertiesPath = projectPath + "/conf";

	/** properties values */
	private static Set<Entry<Object, Object>> sources_values = null;

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

	}

}