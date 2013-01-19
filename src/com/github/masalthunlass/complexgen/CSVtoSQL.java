package com.github.masalthunlass.complexgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CSVtoSQL {

	private static HashMap<String, String> CSVFiles;
	private static HashMap<String, String> SQLFiles;
	private static String SQLString;

	private static void initPathFiles() {
		CSVFiles = new HashMap<String, String>();
		CSVFiles.put("2006", "/resources/data/sql/isf/isf_2006.csv");
		CSVFiles.put("2007", "/resources/data/sql/isf/isf_2007.csv");
		CSVFiles.put("2008", "/resources/data/sql/isf/isf_2008.csv");
		CSVFiles.put("2009", "/resources/data/sql/isf/isf_2009.csv");
		CSVFiles.put("2010", "/resources/data/sql/isf/isf_2010.csv");

		SQLFiles = new HashMap<String, String>();
		SQLFiles.put("2006", "/resources/data/sql/isf/isfInsert2006.sql");
		SQLFiles.put("2007", "/resources/data/sql/isf/isfInsert2007.sql");
		SQLFiles.put("2008", "/resources/data/sql/isf/isfInsert2008.sql");
		SQLFiles.put("2009", "/resources/data/sql/isf/isfInsert2009.sql");
		SQLFiles.put("2010", "/resources/data/sql/isf/isfInsert2010.sql");
	}

	private static File getResourceInput(String fileName) {
		final File f = new File("");
		final String completeFileName = f.getAbsolutePath() + File.separator
				+ fileName;
		File file = new File(completeFileName);
		return file;
	}

	private static java.util.List<String> readCSVFile(File file)
			throws FileNotFoundException, IOException {
		java.util.List<String> result = new ArrayList<String>();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			result.add(line);
		}
		br.close();
		fr.close();
		return result;
	}

	private static void addInsertOrders(java.util.List<String> lines) {
		SQLString = "";
		String sep = new Character('\t').toString();
		for (String line : lines) {
			String[] column = line.split(sep);
			String col1 = formatInseeCode(column[0]);
			SQLString += "INSERT INTO impot VALUES " + "( '" + col1 + "' , "
					+ column[1].trim() + " , " + column[2].trim() + " , "
					+ column[3].trim() + " , " + column[4].trim() + " ) ; \n";
		}
	}

	private static String formatInseeCode(String column) {
		String col1 = column.trim();
		col1 = col1.replaceAll("\"", "");
		col1 = col1.replaceAll(" ", "");
		if (col1.length() == 4) {
			col1 = "0" + col1;
		}
		return col1;
	}

	private static void writeSQlFile(String fichier) throws IOException {
		String path = new File("").getAbsolutePath();
		File f = new File(path + "/" + fichier);
		FileWriter fw = new FileWriter(f);
		fw.write(SQLString);
		fw.close();
	}

	private static void doCSVtoRDF() throws IOException {
		Set<String> years = CSVFiles.keySet();
		for (String year : years) {
			File csv = getResourceInput(CSVFiles.get(year));
			java.util.List<String> lines = readCSVFile(csv);
			addInsertOrders(lines);
			writeSQlFile(SQLFiles.get(year));
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initPathFiles();
		try {
			doCSVtoRDF();
		} catch (IOException e) {
			System.out.println("erreur :" + e.getMessage());
		}

	}

}
