package arqueroMagico;

public class Target {
	
	private Coordenada posicion;
	private int ancho;
	
	public Target(double distanciaArquero, int ancho) {
		this.posicion = new Coordenada(distanciaArquero,100,0);
		
		//El ancho se utiliza para calcular un "area" donde puede impactar la flecha.
		//Se podria llamar diametro directamente.
		this.ancho = 20;
	}
	
	boolean impactoPositivo(Coordenada coordenadaImpacto) {
		//Dio en el centro. Muy poco probable.
		if (impactoExacto(coordenadaImpacto))
			return true;
		
		//Verificar que la coordenada de impacto de la flecha en el ejeX sea >= a distancia target.
		//Es decir, que no se haya quedado corta.
		if(coordenadaImpacto.x < this.posicion.x)
			return false;
		
		
		
		//Da en otros lados. Entre un rango X e Y dependiendo el ancho del target.
		if (coordenadaImpacto.x == this.posicion.x) {
			if (coordenadaImpacto.y < this.posicion.y + (ancho/2) && coordenadaImpacto.y > this.posicion.y - (ancho/2)) {
				return true;

//				la coordenada Z todavia no influye. El target esta fijo.	
//				if (cImpacto.z < this.posicion.z + (ancho/2) && cImpacto.z > this.posicion.z - (ancho/2)) {
//					return true;
//				}
//				return false;
				
			}
			return false;
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
