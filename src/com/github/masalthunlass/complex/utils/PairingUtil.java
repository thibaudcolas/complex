package com.github.masalthunlass.complex.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.masalthunlass.complex.enums.DataEnum;
import com.github.masalthunlass.complex.enums.SourcesEnum;

public class PairingUtil {
	public static Boolean verify(DataEnum data, SourcesEnum source)
			throws FileNotFoundException, IOException {
		System.out.println("verifying");
		Boolean verified = false;

		String available = PropertiesUtil.getSourcesProperty(data.toString()
				.toLowerCase() + ".availablein");
		if (available != null) {
			String[] tokens = available.split(",");
			for (int i = 0; i < tokens.length; ++i) {
				if (source.toString().equalsIgnoreCase(tokens[i]))
					verified = true;
			}
		}
		return verified;
	}
}
