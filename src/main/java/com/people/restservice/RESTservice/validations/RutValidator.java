package com.people.restservice.RESTservice.validations;

import java.util.regex.Pattern;

public class RutValidator {

	public static boolean isValid(String rut) {
                boolean flag = false;
		if (rut == null || rut.isEmpty()) {
			flag = false;
		}

		//String[] rutArray = rut.replace(".", "").split("-");
		//return validateRut(Integer.parseInt(rutArray[0]), rutArray[1].toUpperCase().charAt(0));
                
                String regex = "[\\d]{1,2}.[\\d]{3}.[\\d]{3}-[\\dkK]{1}";
                if(Pattern.matches(regex,rut)){
                    flag = true;
                }
                
                return flag;
	}

	private static boolean validateRut(int rut, char dv) {
		int m = 0;
		int s = 1;

		for (; rut != 0; rut /= 10) {
			s = (s + rut % 10 * (9 - m++ % 6)) % 11;
		}
		return dv == (char) (s != 0 ? s + 47 : 75);
	}
}
