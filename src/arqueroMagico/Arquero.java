package arqueroMagico;

public class Arquero {
	
	private Flecha[] flechas;
	private int impactosPositivos;
	
	public Arquero(int cantidadDisparos) {
		this.flechas = new Flecha[cantidadDisparos];
		this.impactosPositivos = 0;
	}

	public void dispararAMansalva(Target objetivo, double anguloInicial, double velocidadInicial)
	{	
		//Dado un angulo inicial y una velocidada inicial comienza a disparar las
		//flechas provistas hasta que se le acaban las flechas.
		//Aumenta el angulo y la velocidad en cada tiro.
		
		
		double anguloX = anguloInicial;
		double velocidadX = velocidadInicial; // m/s
		double contador = 0;
		
		for (Flecha f:flechas) {
			f.setAngulo(anguloX);
			f.setVelInicial(velocidadX);
			f.calcularCoordenadaFinal(objetivo.getDistancia());
			
			if (objetivo.impactoPositivo(f.getCoordenadaFinal())){
				this.impactosPositivos++;
				
				//No deberian estar los out, pero queda claro donde pega.
				System.out.println("ImpactoPositivo: " + impactosPositivos);
			}
			
			System.out.println("X: " + f.getCoordenadaFinal().x + "Y: " + Math.round(f.getCoordenadaFinal().y));

			//aumento la vlocidad y el angulo poco a poco. 1 a la vez.
			if (contador%2==0)
				anguloX += 0.05;
			else
				velocidadX += 0.05;
			contador++;
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
				//Dado que el mas minimo cambio de angulo o velocidad afecta mucho al impacto,
				//se ajusta el angulo y la velocidad dividiendo por 4
				
				
				//Me voy acercando ajustando la velocidad incial en base a cuan lejos esta del eje X.
				double difDistanciaAlObjetivo = objetivo.getDistancia() - f.getCoordenadaFinal().x;
				//El objetivo esta más lejos:
				if (difDistanciaAlObjetivo > 100)
					velocidadX += difDistanciaAlObjetivo/(100*4);
				//El objetivo esta mas cerca.
				else if((difDistanciaAlObjetivo) < -100)
					velocidadX += difDistanciaAlObjetivo/(100*4);

				
				//Me acerco calculando el angulo en base a cuan lejos esta del eje Y.
				double difAlturaAlObjetivo = objetivo.getAltura() - f.getCoordenadaFinal().y;
				//EL objetivo esta mas arriba: 
				if (difAlturaAlObjetivo > 1)
					anguloX += difAlturaAlObjetivo/(100*4);
				//El objetivo esta mas abajo:
				else if((difAlturaAlObjetivo) < -1)
					anguloX += difAlturaAlObjetivo/(100*4);
			}			
			//Muestra el impacto..comentar.
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
