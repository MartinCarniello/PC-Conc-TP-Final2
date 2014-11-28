package movimientos;

import ocupantes.Participante;
import tablero.Celda;

public class MovimientoIzquierda extends Movimiento {

	public MovimientoIzquierda(Participante participante) {
		super(participante);
	}
	
	@Override
	public Celda celdaAMoverse() {
		return this.getParticipante().getEquipo().celdaAIzq(
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
