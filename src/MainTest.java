import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.masalthunlass.complex.enums.DataEnum;
import com.github.masalthunlass.complex.enums.SourcesEnum;
import com.github.masalthunlass.complex.exceptions.PairingException;
import com.github.masalthunlass.complex.model.PairingDescription;


public class MainTest {

	/**
	 * @param args
	 * @throws PairingException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws PairingException, FileNotFoundException, IOException {
		PairingDescription desc = new PairingDescription();
		System.out.println(desc.definePairing(DataEnum.ISF, SourcesEnum.D2RQ));
		System.out.println(desc.definePairing(DataEnum.GEONAMES, SourcesEnum.D2RQ));
		System.out.println(desc.definePairing(DataEnum.PASSIM, SourcesEnum.TDB));
		System.out.println(desc.definePairing(DataEnum.INSEE, SourcesEnum.TDB));
		System.out.println(desc.definePairing(DataEnum.MONUMENTS, SourcesEnum.TDB));
		
		System.out.println(desc);
		
	}

}
