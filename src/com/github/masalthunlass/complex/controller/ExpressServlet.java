package com.github.masalthunlass.complex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

/**
 * Servlet implementation class ExpressServlet
 */
@WebServlet("/express")
public class ExpressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/Users/Will/Workspace/complex/resources/data/";
		Model md = ModelFactory.createDefaultModel();
		FileManager.get().readModel(md, path + "rdf/passim.rdf");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache") ;
		response.setHeader("Pragma", "no-cache") ;

		Query query = QueryFactory.create(request.getParameter("query"), Syntax.syntaxARQ);
		QueryExecution exec = QueryExecutionFactory.create(query, md);

		try {
		ResultSet rs = exec.execSelect();
		ServletOutputStream out = response.getOutputStream();
		ResultSetFormatter.outputAsJSON(out, rs);
		            out.flush();
		            response.flushBuffer();

		//ResultSetFormatter.out(System.out, rs, query);
		} catch (Exception e) {
		} finally {
		exec.close();

		}
	}

}
