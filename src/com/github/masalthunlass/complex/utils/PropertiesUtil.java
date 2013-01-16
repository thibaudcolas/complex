package com.github.masalthunlass.complex.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Classe permettant la lecture des proprietés de l'application
 */
public class PropertiesUtil {
	public static Logger logger = (Logger) Logger
			.getLogger(PropertiesUtil.class);

	/** Properties filenames */
	private static String SOURCES_PATH = "sources.properties";

	/** Values */
	private static String projectPath = System.getProperty("user.dir");
	private static String webAppPath = projectPath + "/complex";
	private static String webInfPath = webAppPath + "/WEB-INF";
	private static String srcPath = projectPath + "/src";
	private static String propertiesPath = projectPath + "/conf";

	/** properties values */
	private static Set<Entry<Object, Object>> sources_values = null;

	public static String getSourcesProperty(String key) throws FileNotFoundException, IOException {
		Iterator it = getSourcesValues().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
			String entry_key = (String) entry.getKey();
			String entry_value = (String) entry.getValue();

			if (entry_key.equalsIgnoreCase(key))
				return entry_value;

		}
		return null;
	}

	private static Set<Entry<Object, Object>> getSourcesValues()
			throws FileNotFoundException, IOException {
		if (sources_values == null)
			loadProperties();
		return sources_values;
	}

	private static void loadProperties() throws FileNotFoundException,
			IOException {
		Properties properties = new Properties();
		// Loading sources properties
		properties
				.load(new FileInputStream(propertiesPath + "/" + SOURCES_PATH));
		sources_values = properties.entrySet();

	}

}