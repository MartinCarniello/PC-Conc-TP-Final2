package simulacion;

import movimientos.Movimiento;
import movimientos.MovimientoAdelante;
import movimientos.MovimientoDerecha;
import movimientos.MovimientoIzquierda;
import ocupantes.Participante;
import tablero.Tablero;

public class Simulacion {
	
	public static void main(String[] args) {
		
		// Inicializar tablero
		Tablero tablero = Tablero.init(3, 3);
		
		// Inicializar participantes
		Participante participanteSur1 = new Participante(2, 1);
		Participante participanteSur2 = new Participante(1, 1);
		Participante participanteNorte1 = new Participante(1, 6);
		
		// Movimientos de participante Sur 1
		new MovimientoIzquierda(participanteSur1);
		new MovimientoAdelante(participanteSur1);
		new MovimientoAdelante(participanteSur1);
		new MovimientoAdelante(participanteSur1);
		new MovimientoAdelante(participanteSur1);
		new MovimientoAdelante(participanteSur1);
		new MovimientoIzquierda(participanteSur1);
		new MovimientoDerecha(participanteSur1);
		new MovimientoDerecha(participanteSur1);
		new MovimientoDerecha(participanteSur1);
		new MovimientoDerecha(participanteSur1);
		new MovimientoDerecha(participanteSur1);
		new MovimientoDerecha(participanteSur1);
	
		// Movimientos de participante Sur 2
		new MovimientoAdelante(participanteSur2);
		new MovimientoIzquierda(participanteSur2);
		new MovimientoAdelante(participanteSur2);
		
		// Movimientos de participante Norte 1
		new MovimientoAdelante(participanteNorte1);
		new MovimientoAdelante(participanteNorte1);
		new MovimientoDerecha(participanteNorte1);
		
		
		participanteSur1.start();
		participanteSur2.start();
		
		participanteNorte1.start();

		while(true) {
			tablero.getTablero().imprimirTablero();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
