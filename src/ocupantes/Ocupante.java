package ocupantes;

import tablero.Celda;

public interface Ocupante {
	public boolean esTesoroDeMiEquipo(Participante participante);
	
	public boolean esTesoroEnemigo(Participante participante);
	
	public String formatoDeOcupante();
	
	public void setCeldaActual(Celda celda);
}
