import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.masalthunlass.complex.enums.DataEnum;
import com.github.masalthunlass.complex.enums.SourcesEnum;
import com.github.masalthunlass.complex.exceptions.PairingException;
import com.github.masalthunlass.complex.model.PairingDescription;
import com.github.masalthunlass.complex.utils.PairingUtil;


public class MainTest {

	/**
	 * @param args
	 * @throws PairingException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws PairingException, FileNotFoundException, IOException {
//		PairingDescription desc = new PairingDescription();
//		desc.definePairing(DataEnum.ISF, SourcesEnum.D2RQ);
//		desc.definePairing(DataEnum.GEONAMES, SourcesEnum.TDB);
//		
//		System.out.println(desc);
		
		System.out.println(PairingUtil.verify(DataEnum.GEONAMES, SourcesEnum.TDB));
	}

}
