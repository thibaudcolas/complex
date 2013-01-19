import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.LinkedList;

import com.github.masalthunlass.complex.exceptions.ModelException;
import com.github.masalthunlass.complex.exceptions.PairingException;
import com.github.masalthunlass.complex.model.PairingDescription;
import com.github.masalthunlass.complex.model.enums.DataEnum;
import com.github.masalthunlass.complex.model.enums.SourcesEnum;
import com.github.masalthunlass.complex.model.utils.ModelUtil;
import com.github.masalthunlass.complex.model.utils.PairingUtil;
import com.github.masalthunlass.complex.model.utils.StatsUtil;
import com.github.masalthunlass.complex.model.utils.StatsUtil.Unit;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.Model;

public class MainTest {

	/**
	 * @param args
	 * @throws PairingException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ModelException
	 */
	public static void main(String[] args) throws PairingException,
			FileNotFoundException, IOException, ModelException {
		// PairingDescription desc = new PairingDescription();
		// System.out.println(desc.definePairing(DataEnum.ISF,
		// SourcesEnum.D2RQ));
		// System.out.println(desc.definePairing(DataEnum.GEONAMES,
		// SourcesEnum.TDB));
		// System.out.println(desc.definePairing(DataEnum.PASSIM,
		// SourcesEnum.TDB));
		// System.out.println(desc.definePairing(DataEnum.INSEE,
		// SourcesEnum.TDB));
		// System.out.println(desc.definePairing(DataEnum.MONUMENTS,
		// SourcesEnum.TDB));
		//
		// System.out.println(desc);

		// System.out.println(PairingUtil.getAllAvailableData());
		// System.out.println(PairingUtil.getAllAvailableSources());

		System.out.println(StatsUtil.formatMemory(
				StatsUtil.getMemoryUsed(Runtime.getRuntime()), Unit.MB));

		PairingDescription desc = new PairingDescription();
		desc.definePairing(DataEnum.GEONAMES, SourcesEnum.MEMORY);
		desc.definePairing(DataEnum.PASSIM, SourcesEnum.TDB);
		desc.definePairing(DataEnum.INSEECOG, SourcesEnum.SDB);
		desc.definePairing(DataEnum.INSEEPOP, SourcesEnum.TDB);
		desc.definePairing(DataEnum.ISF, SourcesEnum.D2RQ);
		desc.definePairing(DataEnum.MONUMENTS, SourcesEnum.SDB);

		Model model = desc.getCorrespondingModel();
		// Model model = ModelUtil.generateModel(DataEnum.GEONAMES,
		// SourcesEnum.SDB);

		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "SELECT ?type count(?s) { ?s rdf:type ?type } GROUP BY ?type";

		Query query = QueryFactory.create(queryString, Syntax.syntaxARQ);
		QueryExecution exec = QueryExecutionFactory.create(query, model);

		try {
			ResultSet rs = exec.execSelect();
			ResultSetFormatter.outputAsJSON(System.out, rs);
		} catch (Exception e) {
		} finally {
			exec.close();
		}

		System.out.println(StatsUtil.formatMemory(
				StatsUtil.getMemoryUsed(Runtime.getRuntime()), Unit.MB));

	}
}
