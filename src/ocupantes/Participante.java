package ocupantes;

import java.util.ArrayList;
import java.util.List;

import movimientos.Movimiento;

import tablero.Celda;
import tablero.DimensionTablero;
import tablero.Tablero;

public class Participante extends Thread implements Ocupante {
	
	private Celda celdaActual;
	private Equipo equipo;
	private List<Movimiento> movimientos;
	
	public Equipo getEquipo() {
		return this.equipo;
	}
	
	public Celda getCeldaActual() {
		return this.celdaActual;
	}
	
	public void setCeldaActual(Celda celda) {
		this.celdaActual = celda;
	}
	
	public void setEquipo(Celda celda) {
		if(celda.getCoord().getY() <= (DimensionTablero.TAMANIO / 2)) {
			this.equipo = Tablero.getTablero().getEquipoSur();
		} else {
			this.equipo = Tablero.getTablero().getEquipoNorte();
		}
	}
	
	public void agregarMovimiento(Movimiento movimiento) {
		this.movimientos.add(movimiento);
	}

	public Participante(int x, int y) {
		Celda celda = Tablero.getTablero().buscarCelda(x, y);
		this.celdaActual = celda;
		this.setEquipo(celda);
		this.celdaActual.setOcupante(this);
		this.celdaActual.setEstaLibre(false);
		this.movimientos = new ArrayList<Movimiento>();
	}
	
	public void run() {
		for(Movimiento movimiento : this.movimientos) {
//			double random = Math.random() * 2000;
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			movimiento.mover();
		}
	}

	@Override
	public boolean esTesoroDeMiEquipo(Participante participante) {
		return false;
	}
	
	@Override
	public boolean esTesoroEnemigo(Participante participante) {
		return false;
	}
	
	@Override
	public String formatoDeOcupante() {
		return this.equipo.formatoDeParticipante();
	}

}
