package tablero;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ocupantes.Ocupante;
import ocupantes.Participante;

public class Celda {
	
	private Lock lock = new ReentrantLock();
	private Condition ocupado = lock.newCondition();
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
	
	/**
	 * Ocupa el casillero de adelante.
	 * Primero chequea que este libre y que haya algun participante
	 * a los costados o atras de la celda actual del participante.
	 * Si no se cumple alguna de estas condiciones, manda a dormir al thread.
	 * Cuando se levanta vuelve a chequear y si devuelve false, 
	 * setea al ocupante con el pasado por parametro, y cambia el estado
	 * de libre a false
	 * @param ocupante
	 */
	public void ocuparCasilleroAdelante(Ocupante ocupante) {
		lock.lock();
		
		while(!this.estaLibre || !hayParticipantesEnLindantes(ocupante)) {
			
			try {
				this.ocupado.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.ocupante = ocupante;
		this.estaLibre = false;
		ocupante.setCeldaActual(this);
		
		lock.unlock();
	}

	/** Ocupa el casillero del costado.
	 * Primero chequea que este libre.
	 * Si no se cumple esta condicion, manda a dormir al thread.
	 * Cuando se levanta vuelve a chequear y si devuelve false, 
	 * setea al ocupante con el pasado por parametro, y cambia el estado
	 * de libre a false
	 * @param ocupante
	 */
	public void ocuparCasilleroLateral(Ocupante ocupante){
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

	/**
	 * Libera la celda, es decir setea al ocupante con null,
	 * y vuelve a setear el estado de libre a true.
	 * Al final hace un signalAll para despertar a todos los threads
	 * para que puedan chequear sus condiciones y moverse o no.
	 */
	public void liberarCasillero(){
		lock.lock();
		
		this.ocupante = null;
		this.estaLibre = true;
		this.ocupado.signalAll();
		
		lock.unlock();
	}
	
	/**
	 * Devuelve true si hay un participante en alguna de las celdas
	 * laterales o traseras de la celda actual del ocupante.
	 * @param ocupante
	 */
	public boolean hayParticipantesEnLindantes(Ocupante ocupante) {
		return this.hayParticipanteAtras(ocupante) ||
				this.hayParticipanteIzquierda(ocupante) ||
				this.hayParticipanteDerecha(ocupante);
	}
	
	public boolean hayParticipanteAtras(Ocupante ocupante) {
		Participante participante = (Participante) ocupante;
		Celda celda = participante.getEquipo().celdaAAtras(ocupante.getCeldaActual());
		if(celda != null) {
			return celda.tieneParticipanteMio(participante);
		} else {
			return false;
		}
		
	}
	
	public boolean hayParticipanteIzquierda(Ocupante ocupante) {
		Participante participante = (Participante) ocupante;
		Celda celda = participante.getEquipo().celdaAIzq(ocupante.getCeldaActual());
		if(celda != null) {
			return celda.tieneParticipanteMio(participante);
		} else {
			return false;
		}
	}
	
	public boolean hayParticipanteDerecha(Ocupante ocupante) {
		Participante participante = (Participante) ocupante;
		Celda celda = participante.getEquipo().celdaADer(ocupante.getCeldaActual());
		if(celda != null) {
			return celda.tieneParticipanteMio(participante);
		} else {
			return false;
		}
	}
	
	/**
	 * Devuelve true si hay un participante del mismo equipo
	 * del participante pasado por parametro en la celda
	 * @param participante
	 */
	public boolean tieneParticipanteMio(Participante participante) {
		return this.ocupante != null && this.ocupante.esParticipanteDeMiEquipo(participante);
	}
	
	/**
	 * Devuelve true si hay un tesoro del mismo equipo
	 * del participante pasado por parametro en la celda
	 * @param participante
	 */
	public boolean hayTesoroMio(Participante participante) {
		return this.ocupante != null && this.ocupante.esTesoroDeMiEquipo(participante);
	}
	
	/**
	 * Devuelve true si hay un tesoro del equipo enemigo
	 * del participante pasado por parametro en la celda
	 * @param participante
	 */
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
