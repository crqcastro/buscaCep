package br.com.cesarcastro.buscacep.support.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Others {

	public static String deAccent(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
}
