package ocupantes;

import java.util.ArrayList;
import java.util.List;

import movimientos.Movimiento;
import movimientos.MovimientoAdelante;
import movimientos.MovimientoDerecha;
import movimientos.MovimientoEsperar;
import movimientos.MovimientoIzquierda;

import tablero.Celda;
import tablero.Tablero;

public class Participante extends Thread implements Ocupante {
	
	private String nombre;
	private Celda celdaActual;
	private Equipo equipo;
	private List<Movimiento> movimientos;
	
	public Equipo getEquipo() {
		return this.equipo;
	}

	@Override
	public Celda getCeldaActual() {
		return this.celdaActual;
	}
	
	@Override
	public void setCeldaActual(Celda celda) {
		this.celdaActual = celda;
	}
	
	/**
	 * Setea el equipo correcto.
	 * Si el participante esta en la zona Norte, le setea el equipo Norte.
	 * Si el participante esta en la zona Sur, le setea el equipo Sur.
	 * @param celda
	 */
	public void setEquipo(Celda celda) {
		if(celda.getCoord().getY() <= (Tablero.getTablero().getDimension() / 2)) {
			this.equipo = Tablero.getTablero().getEquipoSur();
		} else {
			this.equipo = Tablero.getTablero().getEquipoNorte();
		}
	}
	
	public void agregarMovimiento(Movimiento movimiento) {
		this.movimientos.add(movimiento);
	}

	public Participante(String nombre, int x, int y) {
		this.nombre = nombre;
		Celda celda = Tablero.getTablero().buscarCelda(x, y);
		this.celdaActual = celda;
		this.setEquipo(celda);
		this.celdaActual.setOcupante(this);
		this.celdaActual.setEstaLibre(false);
		this.movimientos = new ArrayList<Movimiento>();
		
		long randomSleep = (long) Math.random() * 2000;
		
		new MovimientoAdelante(this);
		new MovimientoDerecha(this);
		new MovimientoIzquierda(this);
		new MovimientoEsperar(this, randomSleep);
		
	}

	@Override
	public boolean esTesoroDeMiEquipo(Participante participante) {
		return false;
	}
	
	@Override
	public boolean esTesoroEnemigo(Participante participante) {
		return false;
	}
	
	
	public boolean soyNorte() {
		return this.getEquipo().soyNorte();
	}

	@Override
	public String formatoDeOcupante() {
		return this.equipo.formatoDeParticipante();
	}

	/**
	 * Devuelve si el participante pasado por parametro es del mismo equipo
	 * que el participante receptor del mensaje.
	 */
	@Override
	public boolean esParticipanteDeMiEquipo(Participante participante) {
		return this.soyNorte() == participante.soyNorte() ;
	}

	/**
	 * Se hace un ciclo infinito, genera un numero random del 0 al 3
	 * para elegir un movimiento de los 4 que hay.
	 * Se duerme un tiempo simulando el turno y 
	 * delega el mover al movimiento elegido.
	 */
	public void run() {
		while(true) {
			int indiceMovimiento = (int) (Math.random() * 4);
			
			Movimiento movimiento = this.movimientos.get(indiceMovimiento);
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			movimiento.mover();
		}
	}

}
