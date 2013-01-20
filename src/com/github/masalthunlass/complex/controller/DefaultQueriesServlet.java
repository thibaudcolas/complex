package com.github.masalthunlass.complex.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.masalthunlass.complex.model.queries.DefaultQueriesLoader;

/**
 * Servlet implementation class DefaultQueriesServlet
 */
public class DefaultQueriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String defaultQueriesFilePath = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DefaultQueriesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Init function
	@Override
	public void init() {
		ServletConfig config = getServletConfig();

		defaultQueriesFilePath = config
				.getInitParameter("default_path_query_file");

		if (defaultQueriesFilePath == null
				|| defaultQueriesFilePath.length() == 0) {
			System.err.println("Please set init-param to define sdb_password");
			defaultQueriesFilePath = null;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DefaultQueriesLoader defaultQueriesLoader = new DefaultQueriesLoader(
				defaultQueriesFilePath);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(defaultQueriesLoader.defaultQueriesToJSON());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
