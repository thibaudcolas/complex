package com.github.masalthunlass.complex.test.access;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.store.DatasetStore;

/**
 * Test de l'API Jena SDB avec le fichier de configuration SDB de PASSIM sur
 * Xeround. TODO S'assurer que le mot de passe / login soient bien références
 * dans les System Properties.
 * 
 * @author ThibWeb
 * @date 13/01/13
 */
public class TestJenaSDB {

	private static Logger LOG = Logger.getLogger(TestJenaSDB.class);

	public static void main(String[] args) {
		String configPath = "./conf/sdb-xeround-complex.ttl";

		// TODO Ajouter login et password.
		System.setProperty("jena.db.user", "sdb-user");
		System.setProperty("jena.db.password", "complex");

		// Must be a DatasetStore to trigger the SDB query engine.
		// Creating a graph from the Store, and adding it to a general purpose
		// dataset will
		// not necesarily exploit full SQL generation. The right answers will be
		// obtained but slowly.
		Store store = SDBFactory.connectStore(configPath);
		Dataset ds = DatasetStore.create(store);

		LOG.info("Loading " + configPath);

		String queryString = "SELECT * { ?s ?p ?o } LIMIT 10";
		LOG.info("Processing " + queryString);

		Query query = QueryFactory.create(queryString);
		QueryExecution exec = QueryExecutionFactory.create(query, ds);

		try {
			ResultSet rs = exec.execSelect();
			ResultSetFormatter.out(System.out, rs, query);
		} catch (Exception e) {
		} finally {
			exec.close();
			// Fermer la connexion SDB ferme aussi la connexion JDBC.
			store.getConnection().close();
			store.close();
		}
	}
}
