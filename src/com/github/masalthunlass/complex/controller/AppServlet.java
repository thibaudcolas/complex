package com.github.masalthunlass.complex.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppServlet
 */
@WebServlet("/AppServlet")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String sdb_user;
	private static String sdb_password;

	// Init function
	@Override
	public void init() {
		ServletConfig config = getServletConfig();

		sdb_user = config.getInitParameter("sdb_user");
		sdb_password = config.getInitParameter("sdb_password");

		if (sdb_user == null) {
			System.err.println("Please set init-param to define sdb_user");
		}
		if (sdb_password == null) {
			System.err.println("Please set init-param to define sdb_password");
		}
	}

	public static String getSdbUser() {
		return sdb_user;
	}

	public static String getSdbPassword() {
		return sdb_password;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * CoolQuery query = new CoolQuery(request); CoolResponse response = new
		 * CoolResponse(query.execute());
		 */
	}

}
