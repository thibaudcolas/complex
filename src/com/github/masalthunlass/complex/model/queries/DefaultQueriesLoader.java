/**
 * 
 */
package com.github.masalthunlass.complex.model.queries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * Loads SPARQL Queries with related metadata. Will be used to send those
 * queries to the client.
 * 
 * @date 190113
 */
public class DefaultQueriesLoader {

	private DefaultQueries defaultQueries;

	/** Default path for the queries file. **/
	private static final String defaultQueriesFilePath = System
			.getProperty("user.dir") + "/conf/default-queries.json";

	public DefaultQueriesLoader() {
		defaultQueries = new DefaultQueries();
		defaultQueriesFromJSON(defaultQueriesFilePath);
	}

	public DefaultQueriesLoader(String queriesFilePath) {
		defaultQueries = new DefaultQueries();
		if (queriesFilePath == null)
			queriesFilePath = defaultQueriesFilePath;
		defaultQueriesFromJSON(queriesFilePath);
	}

	// public void addDefaultQuery(DefaultQuery defaultQuery) {
	// defaultQueries.add(defaultQuery);
	// }
	//
	// public void addDefaultQuery(String name, String title, String
	// description, String string) {
	// defaultQueries.add(new DefaultQuery(name, title, description, string));
	// }
	//
	// public void addDefaultQueries(DefaultQueries defaultQueries) {
	// this.defaultQueries.addAll(defaultQueries);
	// }

	/**
	 * @return the defaultQueries
	 */
	public DefaultQueries getDefaultQueries() {
		return defaultQueries;
	}

	/**
	 * Converts our ArrayList to JSON.
	 * 
	 * @return queriesFilePath as JSON.
	 */
	public String defaultQueriesToJSON() {
		return new Gson().toJson(defaultQueries);
	}

	/**
	 * Parses a JSON file to populate our ArrayList.
	 * 
	 * @param queriesFilePath
	 * @throws FileNotFoundException
	 */
	public void defaultQueriesFromJSON(String queriesFilePath) {
		File jsonFile = new File(queriesFilePath);

		try {
			if (jsonFile.exists() && jsonFile.canRead()) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(new FileInputStream(jsonFile)));
				defaultQueries = new Gson().fromJson(reader,
						defaultQueries.getClass());
			} else {
				throw new FileNotFoundException("File " + jsonFile
						+ " is missing.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
