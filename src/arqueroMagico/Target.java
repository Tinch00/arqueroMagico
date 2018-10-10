package arqueroMagico;

public class Target {
	
	private Coordenada posicion;
	private int ancho;
	private double longitudCuerda;
	private double periodo;
	
	
	public Target(double distanciaArquero, int ancho) {
		this.posicion = new Coordenada(distanciaArquero,100,0);
		
		//El ancho se utiliza para calcular un "area" donde puede impactar la flecha.
		//Se podria llamar diametro directamente.
		this.ancho = ancho;
		
		//3Metros ==> 300 cms
		this.longitudCuerda = 300;
		this.periodo = calculoPeriodo();
	}
	
	private double calculoPeriodo() {
		//El periodo del péndulo simple es el tiempo que tarda el péndulo en volver a pasar 
		//por un punto en el mismo sentido.
		//También se define como el tiempo que tarda en hacerse una oscilación completa.
		
		return (2*Math.PI)*Math.sqrt((this.longitudCuerda/100)/9.8);
	}
	

	//Reformular para incorporar el tiempo:	
	boolean impactoPositivo(Coordenada coordenadaImpacto, double tiempoLlegadaFlecha) {
		//Verificar que la coordenada de impacto de la flecha en el ejeX sea >= a distancia target.
		//Es decir, que no se haya quedado corta.
		if(coordenadaImpacto.x < this.posicion.x)
			return false;
		
		//Conociendo el Periodo del pendulo, se sabe que el pendulo pasará por el eje z == 0 cuando
		//el periodo sea tiempo=T1/4 y tiempo=T3/4.
		//Por esto mismo, si la flecha pasa en algun multiplo de T/4 o T*3/4, existe probabilidad de impacto. 
		if (tiempoLlegadaFlecha % (this.periodo/4) < 0.1 || tiempoLlegadaFlecha % (this.periodo*3/4) < 0.1) {
			//Dio en el centro. Muy poco probable.
			if (impactoExacto(coordenadaImpacto))
				return true;
			
			if (coordenadaImpacto.y < this.posicion.y + (ancho/2) && coordenadaImpacto.y > this.posicion.y - (ancho/2))
				return true;
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
	
	public double getAltura() {
		return this.posicion.y;	
	}

	

	

}
