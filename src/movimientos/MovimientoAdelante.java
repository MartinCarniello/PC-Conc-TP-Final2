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
	
	public void ocuparCasilleroCorrecto(Celda celda) {
		celda.ocuparCasilleroAdelante(this.getParticipante());
	}

	@Override
	public void liberarCasilleroCorrecto(Celda celda) {
		celda.liberarCasillero();
	}
	
}
