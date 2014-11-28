package tablero;

import java.util.Hashtable;

import ocupantes.EquipoNorte;
import ocupantes.EquipoSur;
import ocupantes.Tesoro;

public class Tablero {

	private int dimension;
	private Hashtable<String, Celda> tablero;
	private EquipoNorte equipoNorte;
	private EquipoSur equipoSur;
	private int tesorosNorte;
	private int tesorosSur;
	private static Tablero instance = null;
	
	public EquipoNorte getEquipoNorte() {
		return instance.equipoNorte;
	}
	
	public EquipoSur getEquipoSur() {
		return instance.equipoSur;
	}
	
	public int getDimension() {
		return this.dimension;
	}
	
	public static Tablero init(int dimension, int cantParticipantes, int cantTesoros) {
		instance = new Tablero();
		
		instance.dimension = dimension;
		
		instance.tablero = new Hashtable<String, Celda>();
		
		for(int x = 0; x < instance.dimension; x++) {
			for(int y = 0; y < instance.dimension; y++) {
				Coordenada coord = new Coordenada(x, y);
				String clave = instance.convertirCoordenada(x, y);
				instance.tablero.put(clave, new Celda(coord));
			}
		}
		
		instance.equipoNorte =  new EquipoNorte();
		instance.equipoSur =  new EquipoSur();
		
		instance.tesorosNorte = cantTesoros;
		instance.tesorosSur = cantTesoros;
		
		instance.generarTesoros(cantTesoros);
		
		return instance;
	}
	
	// Precondicion: Se tuvo que haber llamado al metodo "init()"
	public static Tablero getTablero() {
		return instance;
	}
	
	public String convertirCoordenada(int x, int y) {
		return Integer.toString(x).concat(".").concat(Integer.toString(y));
	}
	
	public Celda buscarCelda(int x, int y) {
		if((x < 0 || x >= this.dimension) || (y < 0 || y >= this.dimension)) {
			return null;
		} else {
			return this.tablero.get(this.convertirCoordenada(x, y));
		}
	}
	
	public void generarTesoros(int cantTesoros) {
		int x = 2;
		int y = 2;
		
		for(int i = 0; i < cantTesoros; i++) {
			Celda celda = this.buscarCelda(x, y);
			Tesoro tesoroSur = new Tesoro(celda);
			this.equipoSur.agregarTesoro(tesoroSur);
			celda.setOcupante(tesoroSur);
			x += 2;
		}
		
		x = 0;
		y = this.dimension - 3;
		
		for(int i = 0; i < cantTesoros; i++) {
			Celda celda = this.buscarCelda(x, y);
			Tesoro tesoroNorte = new Tesoro(celda);
			this.equipoNorte.agregarTesoro(tesoroNorte);
			celda.setOcupante(tesoroNorte);
			x += 2;
		}		
	}
	
	public void decrementarTesorosSur() {
		this.tesorosSur -= 1;
	}
	
	public void decrementarTesorosNorte() {
		this.tesorosNorte -= 1;
	}
	
	public boolean terminoElJuego() {
		return this.tesorosNorte == 0 || this.tesorosSur == 0;
	}
	
	public void imprimirTablero() {
		for(int y = instance.dimension - 1; y >= 0 ; y--) {
			String fila = "";
			for(int x = 0; x < instance.dimension; x++) {
				String clave = instance.convertirCoordenada(x, y);
				Celda celda = instance.tablero.get(clave);
				fila = fila + celda.formatoDeCelda();
			}
			System.out.println(fila);
		}
		
		System.out.println("");
	}
}
