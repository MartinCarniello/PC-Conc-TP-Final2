package ocupantes;

import tablero.Celda;
import tablero.Tablero;

public class EquipoNorte extends Equipo {

	@Override
	public int posEnXIzq(Celda celda) {
		return celda.getCoord().getX() + 1;
	}

	@Override
	public int posEnYIzq(Celda celda) {
		return celda.getCoord().getY();
	}

	@Override
	public int posEnYDer(Celda celda) {
		return celda.getCoord().getY();
	}

	@Override
	public int posEnXDer(Celda celda) {
		return celda.getCoord().getX() - 1;
	}
	
	@Override
	public int posEnXAdelante(Celda celda) {
		return celda.getCoord().getX();
	}
	
	@Override
	public int posEnYAdelante(Celda celda) {
		return celda.getCoord().getY() - 1;
	}
	
	@Override
	public String formatoDeParticipante() {
		return "|_N|";
	}

	@Override
	public void decrementarTesoros() {
		Tablero.getTablero().decrementarTesorosNorte();
	}
	
	@Override
	public Equipo equipoEnemigo() {
		return Tablero.getTablero().getEquipoSur();
	}
}
