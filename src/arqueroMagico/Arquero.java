package arqueroMagico;

public class Arquero {
	
	private Flecha[] flechas;
	private int impactosPositivos;
	
	public Arquero(int cantidadDisparos) {
		this.flechas = new Flecha[cantidadDisparos];
		this.impactosPositivos = 0;
	}

	public void dispararAMansalvaMismaVelocidad(Target objetivo, double anguloInicial, double velocidadInicial)
	{	
		//Dado un angulo inicial y una velocidada inicial comienza a disparar
		//Las flechas provistas hasta que se le acaban las flechas.
		//Aumenta el angulo de disparo en 0.1 por tiro.
		
		double anguloX = anguloInicial;
		double velocidadX = velocidadInicial; // m/s
		
		for (Flecha f:flechas) {
			f.setAngulo(anguloX);
			f.setVelInicial(velocidadX);
			
			f.calcularCoordenadaFinal(objetivo.getDistancia()); //40metros
			
//			System.out.println("Angulo: " + anguloX + "Velocidad: " + velocidadX + "Distancia max: " + f.alcanceMaximo());
//			System.out.println("y:" + f.getCoordenadaFinal().y + "x:" + f.getCoordenadaFinal().x);
			
			if (objetivo.impactoPositivo(f.getCoordenadaFinal())){
				this.impactosPositivos++;
				
				//No deberian estar los out, pero queda claro donde pega.
				System.out.println("ImpactoPositivo: " + impactosPositivos);
				System.out.println("X: " + f.getCoordenadaFinal().x + "Y: " + Math.round(f.getCoordenadaFinal().y));
			}
			
			anguloX+=0.1;
		}
	}
	
	public void dispararApuntando(Target objetivo, double anguloInicial, double velocidadInicial) {
		//Dispara primera flecha con los parametros iniciales.
		//Verifica si se no alcanzó la distancia del objetivo -->Ajusta la Velocidad.
		//Luego, si le erro al objetivo --> ajusta el angulo.
		
		double anguloX = anguloInicial;
		double velocidadX = velocidadInicial; // m/s
		
		for (Flecha f:flechas) {
			f.setAngulo(anguloX);
			f.setVelInicial(velocidadX);
			
			f.calcularCoordenadaFinal(objetivo.getDistancia()); //40metros
			
			if (objetivo.impactoPositivo(f.getCoordenadaFinal())){
				this.impactosPositivos++;
				
				//No deberian estar los out, pero queda claro donde pega.
				System.out.println("ImpactoPositivo: " + impactosPositivos);
			}	
			
			if (!objetivo.impactoExacto(f.getCoordenadaFinal())){
				
				//Me voy acercando ajustando la velocidad incial.
				double distanciaAlObjetivo = f.getCoordenadaFinal().x - objetivo.getDistancia();
				if (distanciaAlObjetivo > 100) {
					velocidadX += distanciaAlObjetivo/(100*4);
				}else if((-1*distanciaAlObjetivo) > 100){  //EL objectivo se pasó.
					velocidadX -= distanciaAlObjetivo/(100*4);
				}
				
				//Me acerco calculando el angulo.
				double alturaAlObjetivo = f.getCoordenadaFinal().y - objetivo.getAltura();
				if (alturaAlObjetivo > 1) {
					anguloX += alturaAlObjetivo/(100*4);
				}else if((-1*alturaAlObjetivo) > 1) {
					anguloX -= alturaAlObjetivo/(100*4);
						
				}
			}			
			
			System.out.println("X: " + f.getCoordenadaFinal().x + "Y: " + Math.round(f.getCoordenadaFinal().y));
				
		}
			
			
	}
	


	public void setearDisparos(Flecha[] flechas) {
		this.flechas = flechas;
	}
	
	public int getImpactosPositivos() {
		return this.impactosPositivos;
	}

}
