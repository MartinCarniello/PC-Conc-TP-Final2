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
	
	public void ocuparCasilleroCorrecto(Celda celda) {
		celda.ocuparCasillero(this.getParticipante());
	}

}
