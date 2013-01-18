package com.github.masalthunlass.complexgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.hp.hpl.jena.rdf.model.Model;

import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

public class D2RQtoRDF {
	
	private static final String mappingPath = "./conf/mapping-isf.ttl";
	private static final String sortie  = "./resources/isf.rdf";

	public static void writeRDF(Model rdf) throws IOException	{
		File f = new File(sortie);
		FileWriter fw = new FileWriter(f);
		rdf.write(fw, "RDF/XML-ABBREV");
	    fw.close();
	  }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	  	Model m = new ModelD2RQ("file:" + mappingPath);
	  	try {
	  		System.out.println("generation" + sortie);
	  		writeRDF(m);
	  		System.out.println(sortie + "généré");
	  	} catch(IOException e) {
			System.out.println("erreur :"+e.getMessage());
		}
	  

	}

}
