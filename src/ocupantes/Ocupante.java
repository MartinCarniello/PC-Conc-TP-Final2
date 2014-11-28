package ocupantes;

import tablero.Celda;

public interface Ocupante {
	public boolean esParticipanteDeMiEquipo(Participante participante);
	
	public boolean esTesoroDeMiEquipo(Participante participante);
	
	public boolean esTesoroEnemigo(Participante participante);
	
	public Celda getCeldaActual();
	
	public void setCeldaActual(Celda celda);
	
	public String formatoDeOcupante();
}
