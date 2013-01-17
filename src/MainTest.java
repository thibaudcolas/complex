import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.masalthunlass.complex.exceptions.ModelException;
import com.github.masalthunlass.complex.exceptions.PairingException;
import com.github.masalthunlass.complex.model.PairingDescription;
import com.github.masalthunlass.complex.model.enums.DataEnum;
import com.github.masalthunlass.complex.model.enums.SourcesEnum;
import com.github.masalthunlass.complex.model.utils.ModelUtil;
import com.github.masalthunlass.complex.model.utils.PairingUtil;

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

		System.out.println(ModelUtil.generateModel(DataEnum.PASSIM,
				SourcesEnum.TDB));

	}

}
