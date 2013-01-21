package com.github.masalthunlass.complex.model.queries;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.Syntax;

public class QueryValidator {
	
	private final String queryString;
	private Query query;

	public QueryValidator(String queryString) {
		this.queryString = queryString;
		query = null;
	}

	// Dirty !
	public boolean validate() {
		try {
			query = QueryFactory.create(this.queryString, Syntax.syntaxARQ);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
