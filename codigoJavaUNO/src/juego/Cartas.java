package juego;

public class Cartas {
	
	private String tipo;
	private String color;
	
	public Cartas(String tipo, String color) {
		this.tipo = tipo;
		this.color = color;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setNumero() {
		this.tipo = tipo;
	}
	
	public void setColor() {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "[" + tipo + ", " + color + "]";
	}
	
}
