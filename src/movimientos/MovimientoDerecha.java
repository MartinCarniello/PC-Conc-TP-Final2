package movimientos;

import ocupantes.Participante;
import tablero.Celda;

public class MovimientoDerecha extends Movimiento {

	public MovimientoDerecha(Participante participante) {
		super(participante);
	}

	public Celda celdaAMoverse() {
		return this.getParticipante().getEquipo().celdaADer(
				this.getParticipante().getCeldaActual());
	}
	
	/**
	 * Delega en la celda el moverse al casillero lateral
	 */
	public void ocuparCasilleroCorrecto(Celda celda) {
		celda.ocuparCasilleroLateral(this.getParticipante());
	}

	/**
	 * Libera el casillero pasado por parametro
	 */
	@Override
	public void liberarCasilleroCorrecto(Celda celda) {
		celda.liberarCasillero();
	}

}
