
public class Arquero {
	
	private Flecha[] flechas;
	private int impactosPositivos;
	
	public Arquero(int cantidadDisparos) {
		this.flechas = new Flecha[cantidadDisparos];
		this.impactosPositivos = 0;
	}

	public void dispararAMansalvaMismaVelocidad(Target target, double anguloInicial, double velocidadInicial)
	{	
		double anguloX = anguloInicial;
		double velocidadX = velocidadInicial; // m/s
		Target objetivo = target;
		
		for (Flecha f:flechas) {
			f.setAngulo(anguloX);
			f.setVelInicial(velocidadX);
			
			f.calcularCoordenadaFinal(objetivo.getDistancia()); //40metros
			
			System.out.println("Angulo: " + anguloX + "Velocidad: " + velocidadX + "Distancia max: " + f.alcanceMaximo());
			System.out.println("y:" + f.getCoordenadaFinal().y + "x:" + f.getCoordenadaFinal().x);
			
			if (objetivo.impactoPositivo(f.getCoordenadaFinal())){
				impactosPositivos++;
				System.out.println("ImpactoPositivo: " + impactosPositivos);
			}
			
			anguloX+=0.1;
		}
	}
		
	


	public void setearDisparos(Flecha[] flechas) {
		this.flechas = flechas;
	}
	
	public int getImpactosPositivos() {
		return this.impactosPositivos;
	}
	//muchas flechas
	//Puntos o cantImpactos
	//Logica de los tiros.

}
