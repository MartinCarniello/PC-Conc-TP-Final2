package ocupantes;

import java.util.ArrayList;
import java.util.List;

import tablero.Celda;
import tablero.Tablero;

public abstract class Equipo {

	private List<Tesoro> tesoros;
	
	public List<Tesoro> getTesoros() {
		return this.tesoros;
	}
	
	public Equipo() {
		this.tesoros = new ArrayList<Tesoro>();
	}
	
	public void agregarTesoro(Tesoro tesoro) {
		this.tesoros.add(tesoro);
	}

	public abstract int posEnXIzq(Celda celda);
	
	public abstract int posEnYIzq(Celda celda);
	
	public abstract int posEnYDer(Celda celda);
	
	public abstract int posEnXDer(Celda celda);
	
	public abstract int posEnXAdelante(Celda celda);
	
	public abstract int posEnYAdelante(Celda celda);
	
	public abstract int posEnXAtras(Celda celda);
	
	public abstract int posEnYAtras(Celda celda);
	
	public abstract void decrementarTesoros();
	
	public abstract Equipo equipoEnemigo();
	
	public abstract boolean soyNorte();

	public abstract String formatoDeParticipante();
	
	/**
	 * Busca la celda que tiene a la izquierda de la celda pasada por parametro
	 *  y la devuelve.
	 * @param celda
	 */
	public Celda celdaAIzq(Celda celda) {
		return Tablero.getTablero().buscarCelda(this.posEnXIzq(celda), this.posEnYIzq(celda));
	}

	/**
	 * Busca la celda que tiene a la derecha de la celda pasada por parametro
	 *  y la devuelve.
	 * @param celda
	 */
	public Celda celdaADer(Celda celda) {
		return Tablero.getTablero().buscarCelda(this.posEnXDer(celda), this.posEnYDer(celda));	
	}

	/**
	 * Busca la celda que tiene adelante de la celda pasada por parametro
	 *  y la devuelve.
	 * @param celda
	 */
	public Celda celdaAdelante(Celda celda) {
		return Tablero.getTablero().buscarCelda(this.posEnXAdelante(celda), this.posEnYAdelante(celda));
	}
	
	/**
	 * Busca la celda que tiene atras de la celda pasada por parametro
	 *  y la devuelve.
	 * @param celda
	 */
	public Celda celdaAAtras(Celda celda) {
		return Tablero.getTablero().buscarCelda(this.posEnXAtras(celda), this.posEnYAtras(celda));
	}
	
	/**
	 * Busca el tesoro pasado por parametro en la lista de tesoros del
	 * equipo y lo elimina. Tambien decrementa en uno los tesoros enemigos.
	 * @param tesoro
	 */
	public void quitarTesoro(Tesoro tesoro) {
		this.getTesoros().remove(tesoro);
		this.decrementarTesoros();
	}

	/**
	 * Devuelve si el tesoro pasado por parametro es un tesoro del mismo equipo.
	 * @param tesoro
	 */
	public boolean esTesoroDelEquipo(Tesoro tesoro) {
		return this.tesoros.contains(tesoro);
	}
}
