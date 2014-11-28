package simulacion;

import ocupantes.Participante;
import tablero.Tablero;

public class Simulacion {
	
	public static void main(String[] args) {
		
		// Inicializar tablero
		Tablero tablero = Tablero.init(9, 3, 3);
		
		// Inicializar participantes
		Participante participanteSur1 = new Participante("Sur1", 1, 1);
		Participante participanteSur2 = new Participante("Sur2", 3, 0);
		Participante participanteNorte1 = new Participante("Norte1", 1, 7);
		Participante participanteNorte2 = new Participante("Norte2", 3, 8);
		
		participanteSur1.start();
		participanteSur2.start();
		participanteNorte1.start();
		participanteNorte2.start();

		while(true) {
			tablero.getTablero().imprimirTablero();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
