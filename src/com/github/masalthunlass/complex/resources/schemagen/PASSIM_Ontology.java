package com.github.masalthunlass.complex.resources.schemagen;

import com.hp.hpl.jena.rdf.model.*;

/**
 * @author Auto-generated by schemagen on 13 dec. 2012 05:37
 */
public class PASSIM_Ontology {
	/**
	 * <p>
	 * The RDF model that holds the vocabulary terms
	 * </p>
	 */
	private static Model m_model = ModelFactory.createDefaultModel();

	/**
	 * <p>
	 * The namespace of the vocabulary as a string
	 * </p>
	 */
	public static final String NS = "http://data.lirmm.fr/ontologies/passim";

	/**
	 * <p>
	 * The namespace of the vocabulary as a string
	 * </p>
	 * 
	 * @see #NS
	 */
	public static String getURI() {
		return NS;
	}

	/**
	 * <p>
	 * The namespace of the vocabulary as a resource
	 * </p>
	 */
	public static final Resource NAMESPACE = m_model.createResource(NS);

	/**
	 * <p>
	 * The property "SMSInformation" represents the SMS service of the
	 * transport.
	 * </p>
	 */
	public static final Property SMSInformation = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#SMSInformation");

	/**
	 * <p>
	 * The property "centerTown" represents the main town of the transport.
	 * </p>
	 */
	public static final Property centerTown = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#centerTown");

	/**
	 * <p>
	 * The property "cityThrough" represents a city through by the transport.
	 * This property will be instanciate as many times as there is city through
	 * by the transport.
	 * </p>
	 */
	public static final Property cityThrough = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#cityThrough");

	/**
	 * <p>
	 * The property "comment" represents the comments about the transport.
	 * </p>
	 */
	public static final Property comment = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#comment");

	/**
	 * <p>
	 * The property "department" represents the department of the transport.
	 * </p>
	 */
	public static final Property department = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#department");

	/**
	 * <p>
	 * The property "infoPoint" represents the point of information.
	 * </p>
	 */
	public static final Property infoPoint = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#infoPoint");

	/**
	 * <p>
	 * The property "isAccessibilityForDisabledPerson" represents if the
	 * transport is for the disabled person or not.
	 * </p>
	 */
	public static final Property isAccessibilityForDisabledPerson = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#isAccessibilityForDisabledPerson");

	/**
	 * <p>
	 * The property "isWebSiteAccessibilityForDisabledPerson" represents if the
	 * website of the transport is for the disabled person or not.
	 * </p>
	 */
	public static final Property isWebSiteAccessibilityForDisabledPerson = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#isWebSiteAccessibilityForDisabledPerson");

	/**
	 * <p>
	 * The property "landInformation" represents the informations about the land
	 * through by the transport.
	 * </p>
	 */
	public static final Property landInformation = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#landInformation");

	/**
	 * <p>
	 * The property "mobileApplication" represents the mobile application of the
	 * transport.
	 * </p>
	 */
	public static final Property mobileApplication = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#mobileApplication");

	/**
	 * <p>
	 * The property "modeOfTransport" represents a mode of transport. This
	 * property will be instanciate as many times as there is mode of transport.
	 * </p>
	 */
	public static final Property modeOfTransport = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#modeOfTransport");

	/**
	 * <p>
	 * The property "postalCode" represents the the postal code of the main town
	 * of the transport.
	 * </p>
	 */
	public static final Property postalCode = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#postalCode");

	/**
	 * <p>
	 * The property "region" represents the region of the transport.
	 * </p>
	 */
	public static final Property region = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#region");

	/**
	 * <p>
	 * The property "remark" represents the remarks about the transport.
	 * </p>
	 */
	public static final Property remark = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#remark");

	/**
	 * <p>
	 * The property "serviceCoverage" represents the coverage of the transport.
	 * </p>
	 */
	public static final Property serviceCoverage = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#serviceCoverage");

	/**
	 * <p>
	 * The property "serviceName" represents the name of the service.
	 * </p>
	 */
	public static final Property serviceName = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#serviceName");

	/**
	 * <p>
	 * The property "typeOfService" represents a type of service.
	 * </p>
	 */
	public static final Property typeOfService = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#typeOfService");

	/**
	 * <p>
	 * The property "webSite" represents the Web site of the transport.
	 * </p>
	 */
	public static final Property webSite = m_model
			.createProperty("http://data.lirmm.fr/ontologies/passim#webSite");

	/**
	 * <p>
	 * This class represents the coverage of a service of transport.
	 * </p>
	 */
	public static final Resource Coverage = m_model
			.createResource("http://data.lirmm.fr/ontologies/passim#Coverage");

	/**
	 * <p>
	 * This class represents the differents modes of transport.
	 * </p>
	 */
	public static final Resource Mode = m_model
			.createResource("http://data.lirmm.fr/ontologies/passim#Mode");

	/**
	 * <p>
	 * This class represents the services of transport.
	 * </p>
	 */
	public static final Resource Service = m_model
			.createResource("http://data.lirmm.fr/ontologies/passim#Service");

	/**
	 * <p>
	 * This class represents an information service of transport.
	 * </p>
	 */
	public static final Resource TransportServiceInformation = m_model
			.createResource("http://data.lirmm.fr/ontologies/passim#TransportServiceInformation");

	public static final Resource passim = m_model
			.createResource("http://data.lirmm.fr/ontologies/passim");

	public static final Resource rdf = m_model
			.createResource("http://data.lirmm.fr/ontologies/passim#rdf");

	public static final Resource snake = m_model
			.createResource("http://data.lirmm.fr/ontologies/passim#snake");

	public static final Resource ttl = m_model
			.createResource("http://data.lirmm.fr/ontologies/passim#ttl");

}
