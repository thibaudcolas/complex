/*
 * Décrit les couplages jeux de données / SGD fournis par la
 * requête lors du post du formulaire.
 * La mérhode getCorrespondingModel permet de récupérer le 
 * model correspondant.
 * 
 *  Vérifier dans les fichiers de conf que les couplages sont possible !
 */

package com.github.masalthunlass.complex.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.github.masalthunlass.complex.enums.DataEnum;
import com.github.masalthunlass.complex.enums.SourcesEnum;
import com.github.masalthunlass.complex.exceptions.PairingException;
import com.github.masalthunlass.complex.utils.PairingUtil;

/**
 * Classe décrivant le couplage entre les jeux de données et les sources de
 * données. Ces sources et données sont définies dans le fichier
 * sources.properties du répertoire conf/
 * 
 * @author thibaut
 * 
 */

public class PairingDescription {

	private Set<Entry<DataEnum, SourcesEnum>> pairing;

	public PairingDescription() {
		pairing = new HashSet<Entry<DataEnum, SourcesEnum>>();
		System.out.println("new pairing description");
	}

	/**
	 * Définition d'un nouveau couplage données / source. Lors de l'ajout d'un
	 * pairing, la méthode utilise l'utilitaire de Pairing (PaitingUtil) afin de
	 * vérifier si le couplage est possible.
	 * 
	 * @param data
	 *            Le jeu de donnée
	 * @param source
	 *            Le système de stockage
	 * @return Vrai si le pairing est complet (tous les jeux sont associés à un
	 *         système de stockage), Faux sinon
	 * @throws PairingException
	 *             En cas de pairing interdit
	 * @throws FileNotFoundException
	 *             Si le fichier de configuration n'est pas présent
	 * @throws IOException
	 *             Si le fichier de configuration n'est pas accessible
	 */
	public Boolean definePairing(DataEnum data, SourcesEnum source)
			throws PairingException, FileNotFoundException, IOException {
		if (!PairingUtil.verify(data, source))
			throw new PairingException("Couplage Données / Source interdit");

		Iterator<Entry<DataEnum, SourcesEnum>> it = pairing.iterator();
		Boolean ok = false;
		while (it.hasNext()) {
			Entry<DataEnum, SourcesEnum> entry = (Entry<DataEnum, SourcesEnum>) it
					.next();
			DataEnum entry_key = entry.getKey();
			if (entry_key.equals(data)) {
				entry.setValue(source);
				ok = true;
				System.out.println("override");
			}
		}

		if (!ok) {
			Entry<DataEnum, SourcesEnum> entry = new MyEntry<DataEnum, SourcesEnum>(
					data, source);
			pairing.add(entry);
			System.out.println("new");
		}

		return complete();
	}

	/**
	 * Permet l'accès au système de stockage de données associé à un jeu de
	 * donnée.
	 * 
	 * @param data
	 *            Le jeu de données
	 * @return Le système de stockage de données associé
	 */
	public SourcesEnum getSource(DataEnum data) {
		Iterator<Entry<DataEnum, SourcesEnum>> it = pairing.iterator();
		while (it.hasNext()) {
			Entry<DataEnum, SourcesEnum> entry = (Entry<DataEnum, SourcesEnum>) it
					.next();
			DataEnum entry_key = entry.getKey();
			if (entry_key.equals(data))
				return entry.getValue();
		}
		return null;
	}

	/**
	 * Permet de savoir si cette définition de Pairing est complete (Complet =
	 * si chaque jeu de donné est associé à un système de stockage).
	 * 
	 * @return Vrai si complet, Faux sinon
	 */
	public Boolean complete() {
		DataEnum[] data = DataEnum.values();
		for (DataEnum d : data) {
			if (getSource(d) == null)
				return false;
		}
		return true;
	}

	// TODO Model getCorrespondingModel()

	public String toString() {
		Iterator<Entry<DataEnum, SourcesEnum>> it = pairing.iterator();
		String retour = "[";
		while (it.hasNext()) {
			Entry<DataEnum, SourcesEnum> entry = (Entry<DataEnum, SourcesEnum>) it
					.next();
			DataEnum entry_key = entry.getKey();
			SourcesEnum entry_value = entry.getValue();
			retour += "\n\t" + entry_key + " -> " + entry_value;
		}
		retour += "\n] complete = " + complete();
		return retour;
	}
}
