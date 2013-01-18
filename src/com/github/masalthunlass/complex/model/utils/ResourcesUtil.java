package com.github.masalthunlass.complex.model.utils;

import java.util.HashMap;

import com.github.masalthunlass.complex.model.enums.DataEnum;

public class ResourcesUtil {

	private static HashMap<String, String> file_path;
	private static String projectPath = System.getProperty("user.dir");

	public static String getProjectPath() {
		return projectPath;
	}

	private static void initPathFiles() {
		file_path = new HashMap<String, String>();
		file_path.put("inseepop", "/resources/data/rdf/inseepop.rdf");
		file_path.put("inseecog", "/resources/data/rdf/inseecog.rdf");
		file_path.put("geonames", "/resources/data/rdf/geonames.rdf");
		file_path.put("passim", "/resources/data/rdf/passim.rdf");
		file_path.put("isf", "/resources/data/rdf/isf.rdf");
		file_path.put("monuments", "/resources/data/rdf/monuments.rdf");
	}

	public static HashMap<String, String> getFilePath() {
		if (file_path == null)
			initPathFiles();
		return file_path;
	}

	public static String getDataPath(DataEnum data) {
		if (file_path == null)
			initPathFiles();
		switch (data) {
		case INSEECOG:
			return file_path.get("inseecog");
		case INSEEPOP:
			return file_path.get("inseepop");
		case GEONAMES:
			return file_path.get("geonames");
		case PASSIM:
			return file_path.get("passim");
		case ISF:
			return file_path.get("isf");
		case MONUMENTS:
			return file_path.get("monuments");
		default:
			return null;
		}
	}
}
