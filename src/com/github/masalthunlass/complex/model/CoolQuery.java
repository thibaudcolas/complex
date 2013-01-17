/*
 * Encapsule une requête sparql (texte) + définition des couplages (PairingDescription)
 * Fournit une méthode execute permettant d'executer la requete et fournissant un objet
 * CoolResponse.
 */

package com.github.masalthunlass.complex.model;

import java.util.HashSet;
import java.util.Set;

public class CoolQuery {
	// TODO cette classe CoolQuery :)
	private Set<String> queries;
	private PairingDescription pairing;

	public CoolQuery(PairingDescription p) {
		queries = new HashSet<String>();
		pairing = p;
	}

	public void addQuery(String query) {
		queries.add(query);
	}

	public Set<String> getQueries() {
		return queries;
	}

	public PairingDescription getPairing() {
		return pairing;
	}

	public String toString() {
		String retour = "CoolQuery[\npairing :";
		retour += pairing + "\n";
		retour += "queries :";
		for (String query : queries) {
			retour += "\n" + query;
		}
		retour += "\n]";

		return retour;
	}
}
