package monedas;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;


public class Monedas {
	//Datos que se obtendran de la aplicacion
	private String monedaActuat;
	private String cantidadActual;
	//Objeto Json que guarda los datos obtenidos de convercion 
	private JSONObject conversionRates;
	//Valor de la conversión obtenida
	private Double cantidadCambio;
	//Total de "Monedas" creados
	private static int total = 0;
	
	public Monedas () {
	}
	public Monedas (String monedaActuat, String cantidadActual) {
		this.monedaActuat = monedaActuat;
		this.cantidadActual = cantidadActual;
		total++;
	}
	
	public void setCantidadActual(String cantidadActual) {
		this.cantidadActual = cantidadActual;
	}
	public String getCantidadActual() {
		return cantidadActual;
	}
	public Double getCantidadCambio() {
		return cantidadCambio;
	}
	public static int getTotal() {
		return Monedas.total;
	}
	
	public void obtenerVC() {
		try {
			String endPoint = "https://v6.exchangerate-api.com/v6/460929610a48347ea99c966b/latest/" + this.monedaActuat;
			URL url = new URL(endPoint);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("Ocurrio un error: " + responseCode);
			} else {
				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());
				
				while (scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}
				scanner.close();

				JSONObject jsonObject = new JSONObject(informationString.toString());
				this.conversionRates = new JSONObject(jsonObject.getJSONObject("conversion_rates").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Double Conversion(String monedaCambio) {
		Double valorConvercion = this.conversionRates.getDouble(monedaCambio);
		this.cantidadCambio = Double.parseDouble(this.cantidadActual) * valorConvercion;
		
		return Math.round(this.cantidadCambio * 100.0) / 100.0;
	}
}
