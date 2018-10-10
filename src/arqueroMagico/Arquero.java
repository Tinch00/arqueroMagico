package arqueroMagico;

public class Arquero {
	
	private Flecha[] flechas;
	private int impactosPositivos;
	private Flecha flechaImpacto;
	
	public Arquero(int cantidadDisparos) {
		this.flechas = new Flecha[cantidadDisparos];
		this.impactosPositivos = 0;
		this.flechaImpacto= null;
	}

	public void dispararAMansalva(Target objetivo, double anguloInicial, double velocidadInicial)
	{	
		//Dado un angulo inicial y una velocidada inicial comienza a disparar las
		//flechas provistas hasta que se le acaban las flechas.
		//Aumenta el angulo y la velocidad en cada tiro.
		
		double anguloX = anguloInicial; 		//grados
		double velocidadX = velocidadInicial;	// m/s
		double acumuladoTiempoVuelo = 0; 		//s - Para verificar en que posicion se encuentra el objetivo.
		double tiempoAcomodarFlecha = 1; 		//s
		double contadorFuerzaBruta = 0; 		//para variar el aumento entre velocidad y angulo.
		
		for (Flecha f:flechas) {

			f.setAngulo(anguloX);
			f.setVelInicial(velocidadX);
			f.calcularCoordenadaFinal(objetivo.getDistancia());
			
			acumuladoTiempoVuelo += f.tiempoDeVuelo() + tiempoAcomodarFlecha;
			
			
			if (objetivo.impactoPositivo(f.getCoordenadaFinal(),acumuladoTiempoVuelo)){
				this.impactosPositivos++;
				this.flechaImpacto = f;
//				System.out.println("Impacto positivo");
			
				return;
			}
			
			//aumento la velocidad y el angulo poco a poco. 1 a la vez.
			if (contadorFuerzaBruta < 30 && f.getCoordenadaFinal().x < 4000)
				anguloX += 0.5;
			else {
				if (f.getCoordenadaFinal().y > 90 && f.getCoordenadaFinal().y < 110)
					//Incrementa la velocidad de manera random pero progrsivamente.
					//Si ya e encuentra entre los valores deseaos, la velocidad es random tambien
					//pero no se va del area de impacto.
					velocidadX += Math.random() * (0.05 - (-0.04)) - 0.04;
				else if (f.getCoordenadaFinal().y > 110 )
					velocidadX += -(Math.random()*(0.05));
				else if (f.getCoordenadaFinal().y < 90)
					velocidadX += Math.random()*0.05;
			}
			contadorFuerzaBruta++;
//			System.out.println("X: " + f.getCoordenadaFinal().x + "Y: " + Math.round(f.getCoordenadaFinal().y));
//			System.out.println(objetivo.centroTargetEnFuncionDelTiempo(acumuladoTiempoVuelo));
		}
	}
	
	public void dispararApuntando(Target objetivo, double anguloInicial, double velocidadInicial) {
		//Dispara primera flecha con los parametros iniciales.
		//Verifica si se no alcanzó la distancia del objetivo -->Aumenta la Velocidad.
		//Luego, si le erro al objetivo --> Ajusta el angulo.
		//Finalmente, si sigue errando, aumenta el tiempo de disparo entre flecha y flecha.
		
		double anguloX = anguloInicial;			// grados
		double velocidadX = velocidadInicial; 	// m/s
		double acumuladoTiempoVuelo = 0;		//s - Para verificar en que posicion se encuentra el objetivo.
		double tiempoAcomodarFlecha = 1;		// s
		
		for (Flecha f:flechas) {
			f.setAngulo(anguloX);
			f.setVelInicial(velocidadX);
			
			f.calcularCoordenadaFinal(objetivo.getDistancia());
			
			acumuladoTiempoVuelo += f.tiempoDeVuelo() + tiempoAcomodarFlecha;
			
			if (objetivo.impactoPositivo(f.getCoordenadaFinal(),acumuladoTiempoVuelo)){
				this.impactosPositivos++;
				this.flechaImpacto = f;
				return;
			}	
			
			if (!objetivo.impactoExacto(f.getCoordenadaFinal())){
				//Dado que el mas minimo cambio de angulo o velocidad afecta mucho al impacto,
				//se ajusta el angulo y la velocidad dividiendo por 4
				
				//Ajuste de Velocidad:
				//Me voy acercando ajustando la velocidad incial en base a cuan lejos esta del eje X.
				double difDistanciaAlObjetivo = objetivo.getDistancia() - f.getCoordenadaFinal().x;
				
				//No llegué al objetivo. Me falta al menos 1 metro.
				if (difDistanciaAlObjetivo > 100)
					velocidadX += difDistanciaAlObjetivo/(100*4);
				//Me pasé del objetivo por mucho. Me pasé por mas de 1 metro.
				else if((difDistanciaAlObjetivo) < -100)
					velocidadX += difDistanciaAlObjetivo/(100*4);

				//Ajuste de tiempo:
				//Me acerco calculando el angulo en base a cuan lejos esta del eje Y.
				double difAlturaAlObjetivo = objetivo.getCoordenadaTarget().y - f.getCoordenadaFinal().y;
				
				//EL objetivo esta mas arriba: 
				if (difAlturaAlObjetivo > 1)
					anguloX += difAlturaAlObjetivo/(100*4);
				//El objetivo esta mas abajo:
				else if((difAlturaAlObjetivo) < -1)
					anguloX += difAlturaAlObjetivo/(100*4);
				
				//Ajusta por tiempo y eje Z del objetivo.
				double difTiempoObjetivo = objetivo.getCoordenadaTarget().z - f.getCoordenadaFinal().z;
				
				if(difTiempoObjetivo < 30 && this.impactosPositivos < 0)
					tiempoAcomodarFlecha += objetivo.calculoPeriodo() *3/5;
				else if(difTiempoObjetivo >30 && this.impactosPositivos < 0)
					tiempoAcomodarFlecha += objetivo.calculoPeriodo()/4;
				
					
			}			
			//Muestra los tiros
//			System.out.println("X: " + f.getCoordenadaFinal().x + "Y: " + Math.round(f.getCoordenadaFinal().y));
//			System.out.println("posicion flecha Z" + f.getCoordenadaFinal().z);
		}
		
	}
	
	
	
	
	public void setearDisparos(Flecha[] flechas) {
		this.flechas = flechas;
	}
	
	public Flecha getFlechaImpacto() {
		return this.flechaImpacto;
	}
	
	public int getImpactosPositivos() {
		return this.impactosPositivos;
	}

}
