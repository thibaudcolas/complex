package com.github.masalthunlass.complex.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	public static String SOURCES_PATH = "sources.properties";

	/** Values */
	public static String projectPath = System.getProperty("user.dir");
	public static String webAppPath = projectPath + "/complex";
	public static String webInfPath = webAppPath + "/WEB-INF";
	public static String srcPath = projectPath + "/src";
	public static String propertiesPath = projectPath + "/conf";

	/** properties values */
	public static Set<Entry<Object, Object>> sources_values = null;

	private static Set<Entry<Object, Object>> getSourcesValues() throws FileNotFoundException, IOException {
		if (sources_values == null)
			loadProperties();
		return sources_values;
	}

	private static void loadProperties() throws FileNotFoundException,
			IOException {
		Properties properties = new Properties();
		// Loading sources properties
		properties.load(new FileInputStream(propertiesPath + "/" + SOURCES_PATH));
		sources_values = properties.entrySet();
		
		
	}

}