package movimientos;

import ocupantes.Participante;
import tablero.Celda;

public class MovimientoAdelante extends Movimiento {
	
	public MovimientoAdelante(Participante participante) {
		super(participante);
	}

	public Celda celdaAMoverse() {		
		return this.getParticipante().getEquipo().celdaAdelante(
				this.getParticipante().getCeldaActual());
	}
	
	/**
	 * Delega a la celda el ocupar el casillero adelante
	 */
	public void ocuparCasilleroCorrecto(Celda celda) {
		celda.ocuparCasilleroAdelante(this.getParticipante());
	}

	/**
	 * Libera el casillero pasado por parametro
	 */
	@Override
	public void liberarCasilleroCorrecto(Celda celda) {
		celda.liberarCasillero();
	}
	
}
