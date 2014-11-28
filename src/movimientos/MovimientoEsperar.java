package movimientos;

import ocupantes.Participante;
import tablero.Celda;

public class MovimientoEsperar extends Movimiento {
	
	private long tiempoDeEspera;

	public MovimientoEsperar(Participante participante, long espera) {
		super(participante);
		this.tiempoDeEspera = espera;
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

	@Override
	public void liberarCasilleroCorrecto(Celda celda) {
		// No libera ninguna celda
	}
}
