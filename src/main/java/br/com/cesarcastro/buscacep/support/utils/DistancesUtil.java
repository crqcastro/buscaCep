package br.com.cesarcastro.buscacep.support.utils;

import java.math.BigDecimal;

public class DistancesUtil {

	private static Long RADIUS = 6371L;
	private static BigDecimal RAD_CALC = new BigDecimal(Math.PI / 180);

	public static BigDecimal calculoRaio(BigDecimal coordenadas1[], BigDecimal coordenadas2[]) {
		BigDecimal lat1 = coordenadas1[0];
		BigDecimal lon1 = coordenadas1[1];

		BigDecimal lat2 = coordenadas2[0];
		BigDecimal lon2 = coordenadas2[1];

		BigDecimal dLat = deg2rad(lat1.subtract(lat2));
		BigDecimal dLon = deg2rad(lon1.subtract(lon2));

		Double a = Math.sin(dLat.doubleValue() / 2) * Math.sin(dLat.doubleValue() / 2)
				+ Math.cos(deg2rad(lat1).doubleValue()) * Math.acos(deg2rad(lat2).doubleValue())
						* Math.sin(dLon.doubleValue() / 2) * Math.sin(dLon.doubleValue() / 2);
		
		Double center = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double distancia = RADIUS * center;
		
		return new BigDecimal(distancia);
	}

	private static BigDecimal deg2rad(BigDecimal deg) {
		return deg.multiply(RAD_CALC);
	}
}
