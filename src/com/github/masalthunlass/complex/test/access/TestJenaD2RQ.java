package com.github.masalthunlass.complex.test.access;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;

import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

/**
 * Test de l'API Jena D2RQ avec le mapping des données de l'ISF sur Heroku
 * Postgres. TODO S'assurer que le mot de passe est bien présent dans le fichier
 * de mapping avant exécution.
 * 
 * @author ThibWeb
 * @date 13/01/13
 */
public class TestJenaD2RQ {

	private static Logger LOG = Logger.getLogger(TestJenaD2RQ.class);

	public static void main(String[] args) {
		String mappingPath = "./conf/d2rq-isf.ttl";
		// Le ModelD2RQ est un modèle Jena utilisant un mapping D2RQ.
		Model m = new ModelD2RQ("file:" + mappingPath);

		LOG.info("Loading " + mappingPath);

		String queryString = "SELECT * WHERE {?s ?p ?o} LIMIT 10";
		LOG.info("Processing " + queryString);

		Query query = QueryFactory.create(queryString);
		QueryExecution exec = QueryExecutionFactory.create(query, m);

		try {
			ResultSet rs = exec.execSelect();
			ResultSetFormatter.out(System.out, rs, query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			exec.close();
		}
	}
}
