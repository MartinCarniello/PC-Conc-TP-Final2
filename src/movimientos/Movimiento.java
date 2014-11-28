package movimientos;

import ocupantes.Participante;
import ocupantes.Tesoro;
import tablero.Celda;
import tablero.Tablero;

public abstract class Movimiento {
	private Participante participante;
	
	public Participante getParticipante() {
		return this.participante;
	}
	
	public Movimiento(Participante participante) {
		this.participante = participante;
		participante.agregarMovimiento(this);
	}

	public abstract Celda celdaAMoverse();
	
	public abstract void ocuparCasilleroCorrecto(Celda celda);
	
	public abstract void liberarCasilleroCorrecto(Celda celda);
	
	/**
	 * Chequea si hay una celda donde se quiere mover, si no termino el juego,
	 * y si no hay tesoro de su equipo. Si es asi, se mueve dependiendo
	 * el movimiento que sea. Si hay tesoro enemigo, lo atrapa.
	 * Al final libera el casillero anterior y chequea si se termino el juego.
	 */
	public void mover() {
		Celda celdaAnterior = this.getParticipante().getCeldaActual();
		Celda celdaAMoverse = this.celdaAMoverse();
		
		// Si hay tesoro de mi equipo o si esta en el borde o
		// si no termino el juego, puede hacer el movimiento

		if(celdaAMoverse != null &&
		   !celdaAMoverse.hayTesoroMio(this.getParticipante()) && 
		   !Tablero.getTablero().terminoElJuego()) {
			
			// Chequear si hay tesoro enemigo,
			// lo agarro, es decir, lo quito de la lista de tesoros y
			// decremento en 1 la cantidad de tesoros del equipo del tablero
			
			if(celdaAMoverse.hayTesoroEnemigo(participante)) {
				Tesoro tesoroAAtrapar = (Tesoro) celdaAMoverse.getOcupante();
				this.ocuparCasilleroCorrecto(celdaAMoverse);
				this.getParticipante().getEquipo().equipoEnemigo().quitarTesoro(tesoroAAtrapar);
			} else {
				this.ocuparCasilleroCorrecto(celdaAMoverse);
			}
			
			this.liberarCasilleroCorrecto(celdaAnterior);
			
		}
		
		// Si gano, imprimo termino el juego
		if(Tablero.getTablero().terminoElJuego()) {
			System.out.println("Termino el juego");
		}
		
	}
	
}
