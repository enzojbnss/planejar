package net.ddns.enzojbnss.planejar.util;

import java.time.LocalDate;

public class CompareData {

	public static Boolean validaDataInicial(LocalDate primaria, LocalDate secundaria) {
		System.out.println("data inicio : " + primaria.toString() + " : " + secundaria.toString());
		if (primaria.isBefore(secundaria) || primaria.equals(secundaria)) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean validaDataFinal(LocalDate primaria, LocalDate secundaria) {
		System.out.println("data fim : " + primaria.toString() + " : " + secundaria.toString());
		if (primaria.isAfter(secundaria) || primaria.equals(secundaria)) {
			return true;
		} else {
			return false;
		}
	}
}
