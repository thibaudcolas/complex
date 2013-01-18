package com.github.masalthunlass.complex.model.utils;

import java.text.DecimalFormat;

public class StatsUtil {

	public static long getMemoryUsed(Runtime runtime) {
		return runtime.totalMemory() - runtime.freeMemory();
	}

	public static String formatMemory(long memory_bytes, StatsUtil.Unit unit) {
		DecimalFormat formatter = new DecimalFormat("##,###.##");

		return formatter.format((double) memory_bytes
				/ (double) getDiviseur(unit))
				+ unit.toString().toLowerCase();
	}

	private static int getDiviseur(StatsUtil.Unit unit) {
		int diviseur = 1;
		switch (unit) {
		case B:
			diviseur = 1;
			break;
		case KB:
			diviseur = 1024;
			break;
		case MB:
			diviseur = 1024 * 1024;
			break;
		}

		return diviseur;
	}

	public static enum Unit {
		B, KB, MB;
	}
}
