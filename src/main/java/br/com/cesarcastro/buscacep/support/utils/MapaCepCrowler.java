package br.com.cesarcastro.buscacep.support.utils;

import br.com.cesarcastro.buscacep.domain.model.response.LocationResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MapaCepCrowler {

	public static LocationResponse obterLocation(String cep) throws MalformedURLException, IOException{

		try {
			HttpURLConnection request = (HttpURLConnection) new URL("https://www.google.com/maps/search/" + cep.replaceAll("[^\\d.]", ""))
					.openConnection();

			try {
				request.setRequestMethod("GET");
				request.connect();

				String htmlCode = readResponse(request);
				request.disconnect();
				return getLocationFromHTML(htmlCode, cep.replaceAll("[^\\d.]", ""));
			} finally {
				request.disconnect();
			}
		} catch (IOException ex) {
			//TODO: Logar erro e tratar com excecao certa
			throw ex;
		}
	}

	private static String readResponse(HttpURLConnection request) throws IOException {
		ByteArrayOutputStream os;
		try (InputStream is = request.getInputStream()) {
			os = new ByteArrayOutputStream();
			int b;
			while ((b = is.read()) != -1) {
				os.write(b);
			}
		}
		return new String(os.toByteArray());
	}

	private static LocationResponse getLocationFromHTML(String html, String cep) {
		LocationResponse location = new LocationResponse();
		try {
			String textSearch = "/@";
			String start = html.substring(html.lastIndexOf(textSearch) + textSearch.length());
			String lat = start.substring(0, start.indexOf(","));
			start = start.substring(start.indexOf(start) + lat.length() + 1);
			String lon = start.substring(0, start.indexOf(","));
			location.setLatitude(new BigDecimal(lat));
			location.setLongitude(new BigDecimal(lon));
		} catch (Exception e) {
		}
		return location;
	}
}
