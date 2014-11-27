package movimientos;

import ocupantes.Participante;
import tablero.Celda;

public class MovimientoEsperar extends Movimiento {

	public MovimientoEsperar(Participante participante) {
		super(participante);
	}

	public Celda celdaAMoverse() {
		return 	this.getParticipante().getCeldaActual();
	}
	
	public void ocuparCasilleroCorrecto(Celda celda) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
