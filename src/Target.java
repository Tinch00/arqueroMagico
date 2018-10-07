
public class Target {
	
	private Coordenada posicion;
	private int ancho;
	
	public Target(double distanciaArquero, int ancho) {
		this.posicion = new Coordenada(distanciaArquero,100,0);
		this.ancho = 20;
	}
	
	boolean impactoPositivo(Coordenada cImpacto) {
		//Dio en el centro.
		if (cImpacto == this.posicion)
			return true;
		
		//Verificar que la coordenada de impacto de la flecha en X sea >= a distancia target.
		//Es decir, que no se haya quedado corta.
		if(cImpacto.x < this.posicion.x)
			return false;
		
		
		
		//Da en otros lados. Entre un rango X e Y.
		if (cImpacto.x == this.posicion.x) {
			if (cImpacto.y < this.posicion.y + (ancho/2) && cImpacto.y > this.posicion.y - (ancho/2)) {
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
	
	
	public double getDistancia() {
		return this.posicion.x;
	}

	

}
