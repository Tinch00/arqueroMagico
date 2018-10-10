package arqueroMagico;

public class Target {
	
	private Coordenada posicion;   		// (cm, cm, cm)
	private int ancho;					// cm
	private double longitudCuerda; 		// cm
	private double periodo;				// s
	private double anguloDeMovimiento;  // grados
	
	
	public Target(double distanciaArquero, int ancho) {
		this.posicion = new Coordenada(distanciaArquero,100,0);
		
		//El ancho se utiliza para calcular un "area" donde puede impactar la flecha.
		//Se podria llamar diametro directamente.
		this.ancho = ancho;
		
		//3Metros ==> 300 cms
		this.longitudCuerda = 300;
		this.periodo = calculoPeriodo();
		this.anguloDeMovimiento = 20;
	}
	
	public double calculoPeriodo() {
		//El periodo del péndulo simple es el tiempo que tarda el péndulo en volver a pasar 
		//por un punto en el mismo sentido.
		//También se define como el tiempo que tarda en hacerse una oscilación completa.
		
		return (2*Math.PI)*Math.sqrt((this.longitudCuerda/100)/9.8);
	}
	
	public double centroTargetEnFuncionDelTiempo(double tiempo) {
		//Calculos del Mov Armonico Simple.
		double velAngular = Math.sqrt(9.8/(this.longitudCuerda/100));
		return (((Math.toRadians(this.anguloDeMovimiento)*(this.longitudCuerda/100))*Math.sin(Math.toRadians(velAngular*tiempo)))*100);
	}
	
	boolean impactoPositivo(Coordenada coordenadaImpacto, double tiempoLlegadaFlecha) {
		//Verificar que la coordenada de impacto de la flecha en el ejeX sea >= a distancia target.
		//Es decir, que no se haya quedado corta.
		if(coordenadaImpacto.x < this.posicion.x)
			return false;
		
		this.posicion.z = centroTargetEnFuncionDelTiempo(tiempoLlegadaFlecha);
		
		if(this.posicion.z < 0) {
			//El target se encuentra a la izquerda de la posicion de equilibrio
			if (coordenadaImpacto.z > this.posicion.z && coordenadaImpacto.z < (this.posicion.z + this.ancho/2)) {
				if (coordenadaImpacto.y < this.posicion.y + (ancho/2) && coordenadaImpacto.y > this.posicion.y - (ancho/2))
					return true;
			}
		}else {
			//El target se encuentra a la derecha de la posicion de equilibrio.
			if(coordenadaImpacto.z < this.posicion.z && coordenadaImpacto.z > (this.posicion.z - this.ancho/2)){
				if (coordenadaImpacto.y < this.posicion.y + (ancho/2) && coordenadaImpacto.y > this.posicion.y - (ancho/2))
					return true;
			}
		}
		return false;
	}
	
	public boolean impactoExacto(Coordenada coordenadaImpacto) {
		if (Math.round(coordenadaImpacto.x) == this.posicion.x && Math.round(coordenadaImpacto.y) == this.posicion.y)
			return true;
		return false;
	}
	
	
	public double getDistancia() {
		return this.posicion.x;
	}
	
	public Coordenada getCoordenadaTarget() {
		return this.posicion;
	}

	

	

}
