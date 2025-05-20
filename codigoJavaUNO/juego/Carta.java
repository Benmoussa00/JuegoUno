package juego;

import java.net.URL;

public class Carta {
	
	private String tipo;
	private String color;
	private URL url;
	
	public Carta(String tipo, String color, URL url) {
		this.tipo = tipo;
		this.color = color;
		this.url = url;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getColor() {
		return color;
	}
	
	public URL getUrl() {
		return url;
	}
	
	public void setNumero() {
		this.tipo = tipo;
	}
	
	public void setColor() {
		this.color = color;
	}
	
	public void setUrl() {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "[" + tipo + ", " + color + "]";
	}
	
}
