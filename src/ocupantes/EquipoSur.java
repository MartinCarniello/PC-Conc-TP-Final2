package ocupantes;

import tablero.Celda;
import tablero.Tablero;

public class EquipoSur extends Equipo {

	@Override
	public int posEnXIzq(Celda celda) {
		return celda.getCoord().getX() - 1;
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
		return celda.getCoord().getX() + 1;
	}

	@Override
	public int posEnXAdelante(Celda celda) {
		return celda.getCoord().getX();
	}
	
	@Override
	public int posEnYAdelante(Celda celda) {
		return celda.getCoord().getY() + 1;
	}
	
	@Override
	public int posEnXAtras(Celda celda) {
		return celda.getCoord().getX();
	}

	@Override
	public int posEnYAtras(Celda celda) {
		return celda.getCoord().getY() - 1;
	}
	
	@Override
	public void decrementarTesoros() {
		Tablero.getTablero().decrementarTesorosSur();
	}
	
	@Override
	public Equipo equipoEnemigo() {
		return Tablero.getTablero().getEquipoNorte();
	}

	@Override
	public boolean soyNorte() {
		return false;
	}

	@Override
	public String formatoDeParticipante() {
		return "|_S|";
	}
}
