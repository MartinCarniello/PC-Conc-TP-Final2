package tablero;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ocupantes.Ocupante;
import ocupantes.Participante;

public class Celda {
	
	private Lock lock = new ReentrantLock();
	private Condition ocupado = lock.newCondition();
	private Condition puedeMoverAdelante = lock.newCondition();
	private Coordenada coord;
	private boolean estaLibre;
	private Ocupante ocupante;
	
	public Coordenada getCoord() {
		return coord;
	}

	public void setCoord(Coordenada coord) {
		this.coord = coord;
	}

	public boolean isEstaLibre() {
		return estaLibre;
	}

	public void setEstaLibre(boolean estaLibre) {
		this.estaLibre = estaLibre;
	}
	
	public Ocupante getOcupante() {
		return this.ocupante;
	}
	
	public void setOcupante(Ocupante ocupante) {
		this.ocupante = ocupante;
	}

	public Celda(Coordenada coord) {
		this.setCoord(coord);
		this.setEstaLibre(true);
	}
	
	public boolean esBorde() {
		return this.coord.esBorde();
	}
	
//	public void ocuparCasilleroAdelante(Ocupante ocupante) {
//		lock.lock();
//		
//		while(!this.estaLibre && !hayParticipantesEnLindantes()) {
//			try {
//				this.puedeMoverAdelante.await();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			try {
//				this.ocupado.await();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		this.ocupante = ocupante;
//		
//		lock.unlock();
//	}

	public void ocuparCasillero(Ocupante ocupante){
		lock.lock();
		
		while(!this.estaLibre)
			try {
				this.ocupado.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		this.ocupante = ocupante;
		this.estaLibre = false;
		ocupante.setCeldaActual(this);
		
		lock.unlock();
	}

	public void liberarCasillero(){
		lock.lock();
		
		this.ocupante = null;
		
		this.estaLibre = true;

		this.ocupado.signal();
		
//		this.puedeMoverAdelante.signalAll();
		
		lock.unlock();
	}
	
//	public boolean hayParticipantesEnLindantes() {
//		return this.hayParticipanteAtras() ||
//				this.hayParticipanteIzquierda() ||
//				this.hayParticipanteDerecha();
//	}
	
	public boolean hayTesoroMio(Participante participante) {
		return this.ocupante != null && this.ocupante.esTesoroDeMiEquipo(participante);
	}
	
	public boolean hayTesoroEnemigo(Participante participante) {
		return this.ocupante != null && this.ocupante.esTesoroEnemigo(participante);
	}
	
	public String formatoDeCelda() {
		if(this.ocupante != null) {
			return this.ocupante.formatoDeOcupante();
		} else {
			return "|__|";
		}
	}
	
}
