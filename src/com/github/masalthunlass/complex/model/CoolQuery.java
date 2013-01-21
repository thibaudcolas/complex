/*
 * Encapsule une requête sparql (texte) + définition des couplages (PairingDescription)
 * Fournit une méthode execute permettant d'executer la requete et fournissant un objet
 * CoolResponse.
 */

package com.github.masalthunlass.complex.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.github.masalthunlass.complex.exceptions.CoolQueryException;
import com.github.masalthunlass.complex.exceptions.ModelException;
import com.github.masalthunlass.complex.exceptions.PairingException;
import com.github.masalthunlass.complex.model.utils.StatsUtil;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.Model;

public class CoolQuery {

	private String queryString;
	private PairingDescription pairing;

	public CoolQuery(String q, PairingDescription p) throws CoolQueryException {
		queryString = q;
		pairing = p;
		if (!pairing.complete())
			throw new CoolQueryException(
					"Pairing have to be complete to construct a CoolQuery");
	}

	public CoolResponse execute() throws CoolQueryException {
		try {
			System.gc();
			long time_model = System.currentTimeMillis();
			long memory_model = StatsUtil.getMemoryUsed(Runtime.getRuntime());
			Model model = pairing.getCorrespondingModel();
			memory_model = StatsUtil.getMemoryUsed(Runtime.getRuntime())
					- memory_model;
			time_model = System.currentTimeMillis() - time_model;

			System.gc();
			long time_query = System.currentTimeMillis();
			long memory_query = StatsUtil.getMemoryUsed(Runtime.getRuntime());

			Query query = QueryFactory.create(queryString, Syntax.syntaxARQ);
			QueryExecution exec = QueryExecutionFactory.create(query, model);

			ResultSet rs;

			try {
				rs = exec.execSelect();
			} catch (Exception e) {
				exec.close();
				throw new CoolQueryException("Error while sparql exec ("
						+ e.getMessage() + ").");
			}

			memory_query = StatsUtil.getMemoryUsed(Runtime.getRuntime())
					- memory_query;
			time_query = System.currentTimeMillis() - time_query;

			return new CoolResponse(this, rs, time_model, memory_model,
					time_query, memory_query);

		} catch (PairingException e) {
			throw new CoolQueryException("Oops something goes wrong...");
		}
		catch (ModelException e) {
			throw new CoolQueryException("Oops something goes wrong...");
		} catch (IOException e) {
			throw new CoolQueryException(
					"Problem occured while getting properties files.");
		}
	}

	public PairingDescription getPairing() {
		return pairing;
	}

	public String getQueryString() {
		return queryString;
	}

	public String toString() {
		String retour = "CoolQuery[\npairing :";
		retour += pairing + "\n";
		retour += "query :";
		retour += "\n" + queryString;
		retour += "\n]";

		return retour;
	}
}
