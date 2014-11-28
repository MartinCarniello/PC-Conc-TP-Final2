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
	
	public Celda celdaAIzq(Celda celda) {
		return Tablero.getTablero().buscarCelda(this.posEnXIzq(celda), this.posEnYIzq(celda));
	}

	public Celda celdaADer(Celda celda) {
		return Tablero.getTablero().buscarCelda(this.posEnXDer(celda), this.posEnYDer(celda));	
	}
	
	public Celda celdaAdelante(Celda celda) {
		return Tablero.getTablero().buscarCelda(this.posEnXAdelante(celda), this.posEnYAdelante(celda));
	}
	
	public Celda celdaAAtras(Celda celda) {
		return Tablero.getTablero().buscarCelda(this.posEnXAtras(celda), this.posEnYAtras(celda));
	}
	
	public void quitarTesoro(Tesoro tesoro) {
		this.getTesoros().remove(tesoro);
		this.decrementarTesoros();
	}

	public boolean esTesoroDelEquipo(Tesoro tesoro) {
		return this.tesoros.contains(tesoro);
	}
}
