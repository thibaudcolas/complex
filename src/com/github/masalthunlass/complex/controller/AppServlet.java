package com.github.masalthunlass.complex.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.masalthunlass.complex.exceptions.AppServletException;
import com.github.masalthunlass.complex.exceptions.CoolQueryException;
import com.github.masalthunlass.complex.exceptions.PairingException;
import com.github.masalthunlass.complex.model.CoolQuery;
import com.github.masalthunlass.complex.model.CoolResponse;
import com.github.masalthunlass.complex.model.PairingDescription;
import com.github.masalthunlass.complex.model.enums.DataEnum;
import com.github.masalthunlass.complex.model.enums.SourcesEnum;

/**
 * Servlet implementation class AppServlet
 */

public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String param_query = "query";

	private static String sdb_user = "sdb";
	private static String sdb_password = "password";

	private static String default_path = null;

	// Init function
	@Override
	public void init() {
		ServletConfig config = getServletConfig();

		sdb_user = config.getInitParameter("sdb_user");
		sdb_password = config.getInitParameter("sdb_password");
		default_path = config.getInitParameter("default_path");

		if (sdb_user == null) {
			System.err.println("Please set init-param to define sdb_user");
		}
		if (sdb_password == null) {
			System.err.println("Please set init-param to define sdb_password");
		}
		if (default_path == null) {
			System.err
					.println("PLEASE INIT THE DEFAULT PATH IN THE web.xml !!!");
		}
	}

	public static String getSdbUser() {
		return sdb_user;
	}

	public static String getSdbPassword() {
		return sdb_password;
	}

	public static String getDefaultPath() {
		return default_path;
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

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String sparql = request.getParameter(param_query);
			PairingDescription pairing = new PairingDescription();
			DataEnum[] data = DataEnum.values();
			try {
				for (DataEnum d : data) {
					String dataname = d.toString().toLowerCase();
					String sourcename = (String) request.getParameter(dataname);
					pairing.definePairing(
							DataEnum.valueOf(dataname.toUpperCase()),
							SourcesEnum.valueOf(sourcename.toUpperCase()));
				}
			} catch (NullPointerException e) {
				throw new AppServletException(
						"Some dataset are not defined in the request.");
			} catch (PairingException e) {
				throw new AppServletException(
						"Forbidden pairing are defined in the request.");
			} catch (IllegalArgumentException e) {
				throw new AppServletException(
						"Some dataset or data storages are not defined in this application.");
			}

			CoolQuery query;
			try {
				query = new CoolQuery(sparql, pairing);
				CoolResponse coolResponse;
				coolResponse = query.execute();
				coolResponse.outputAsJSON(response.getOutputStream());

			} catch (CoolQueryException e) {
				throw new AppServletException(
						"Error occured while executing request ("
								+ e.getMessage() + ").");
			}

		} catch (AppServletException e) {
			response.setStatus(400);
			response.setContentType("text/plain");
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			response.setStatus(400);
			response.setContentType("text/plain");
			response.getWriter().write(
					"Unidentified exception: " + e.getMessage());
		}
		/*
		 * CoolQuery query = new CoolQuery(request); CoolResponse response = new
		 * CoolResponse(query.execute());
		 */
	}
}
