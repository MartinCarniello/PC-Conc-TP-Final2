package ocupantes;

import tablero.Celda;
import tablero.Tablero;

public class Tesoro implements Ocupante {
	
	private Celda celda;
	
	public Celda getCelda() {
		return this.celda;
	}
	
	@Override
	public void setCeldaActual(Celda celda) {
		this.celda = celda;
	}
	
	@Override
	public Celda getCeldaActual() {
		return this.celda;
	}
	
	public Tesoro(Celda celda) {
		this.celda = celda;
	}

	@Override
	public boolean esTesoroDeMiEquipo(Participante participante) {
		return participante.getEquipo().esTesoroDelEquipo(this);
	}
	
	@Override
	public boolean esTesoroEnemigo(Participante participante) {
		return participante.getEquipo().equipoEnemigo().esTesoroDelEquipo(this);
	}

	@Override
	public boolean esParticipanteDeMiEquipo(Participante participante) {
		return false;
	}

	@Override
	public String formatoDeOcupante() {
		return "|_T|";
	}

}
