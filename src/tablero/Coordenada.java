package tablero;

public class Coordenada {
	
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean igualA(Coordenada coord) {
		return coord.getX() == this.getX() &&
				coord.getY() == this.getY();
	}
	
	public boolean esBorde() {
		return (this.x == 0 || this.y == 0) ||
				(this.x == DimensionTablero.TAMANIO - 1 || 
				this.y == DimensionTablero.TAMANIO - 1);
	}
}
