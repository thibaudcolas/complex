package com.github.masalthunlass.complex.test.access;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;

/**
 * Test de l'API Jena TDB avec les données de PASSIM. TODO S'assurer que les
 * données aient bien été chargées dans ./tdb/ (non versionné)
 * 
 * @author thibaudcolas
 * @date 14/01/13
 */
public class TestJenaTDB {

    private static Logger LOG = Logger.getLogger(TestJenaTDB.class);

    public static void main(String[] args) {
        String tdbPath = "./tdb/";
        String passimPath = "./data/passim-linked.rdf";

        Dataset ds = TDBFactory.createDataset(tdbPath);
        Model model = ds.getDefaultModel();
        // TODO Décommenter la première fois.
        // FileManager.get().readModel(model, passimPath);

        LOG.info("Loading " + passimPath + " into " + tdbPath);

        String queryString = "SELECT * { ?s ?p ?o } LIMIT 10";
        LOG.info("Processing " + queryString);

        Query query = QueryFactory.create(queryString);
        QueryExecution exec = QueryExecutionFactory.create(query, model);

        try {
            ResultSet rs = exec.execSelect();
            ResultSetFormatter.out(System.out, rs, query);
        } catch (Exception e) {
        } finally {
            exec.close();
            ds.close();
        }
    }
}
