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
	
	/**
	 * Devuelve true si la coordenada pasada por parametro
	 * es igual a la coordenada receptora del mensaje.
	 * @param coord
	 */
	public boolean igualA(Coordenada coord) {
		return coord.getX() == this.getX() &&
				coord.getY() == this.getY();
	}

	/**
	 * Devuelve true si se encuentra en un borde.
	 * "x" o "y" iguales 0
	 * o
	 * "x" o "y" mayores al tamanio del tablero
	 * @return
	 */
	public boolean esBorde() {
		return (this.x == 0 || this.y == 0) ||
				(this.x == Tablero.getTablero().getDimension() - 1 || 
				this.y == Tablero.getTablero().getDimension() - 1);
	}
}
