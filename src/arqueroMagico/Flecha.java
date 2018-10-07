package arqueroMagico;


public class Flecha {
	
	//private Coordenada cInicio;
	private Coordenada cFinal;
	private double velInicial;
	private double angulo;

	//public Flecha(Coordenada cI) {
		//this.cInicio = new Coordenada(cI.x, cI.y, cI.z);
	public Flecha() {
		this.cFinal = new Coordenada(0,0,0);
		this.velInicial = 0;
		this.angulo = 0;
	}

	
	public void calcularCoordenadaFinal(double distanciaDisparo) {
		//Calculo xMax y asigno segun corresponda.
		if (this.alcanceMaximo() < distanciaDisparo) {
			this.cFinal.x = alcanceMaximo();
			this.cFinal.y =- 1;
			return;
		}
		
		this.cFinal.x = distanciaDisparo;
		
		//Divide y triunfaras:
		//Y en funcion de X. Tiro oblicuo. Sin necesidad del Tiempo:
		//Y(x) = Tg(Angulo)*x - (gravedad*x²)/(2*VelIni²*Cos²Angulo)
		//Y(X) =   aux        -      			aux2
		//Y(X) = 			   		aux22    /    aux23 * aux21
		//Dado que se tomo a los ejes en Cms, se multipla y divide por 100 segun corresponda
		//Para que las cuentas den bien con el vlaor de la gravedad.
		
		double aux = Math.tan(Math.toRadians(this.angulo)) * (distanciaDisparo/100);
		double aux21 = Math.pow(Math.cos(Math.toRadians(this.angulo)),2);
		double aux22 = (9.8 * Math.pow((distanciaDisparo/100), 2));
		double aux23 = (2*Math.pow(this.velInicial, 2));
		double aux2 = aux22 / (aux23 * aux21);
		this.cFinal.y = (aux - aux2)*100;
	}
	
	public double alcanceMaximo() {
		double aux = Math.toRadians(this.angulo*2);
		double aux2 = Math.pow(this.velInicial, 2);
		return ((aux2/9.8)*Math.sin(aux))*100;
	}
	
	public double tiempoVuelo() {
		return ((2*this.velInicial)/9.8);
	}
	
	//Cambiar, sacar parametros, reemplazar por this.	
	private double velEnEjeY() {
		return this.velInicial * Math.sin(Math.toRadians(this.angulo));
	}
	
	private double velEnEjeX() {
		return this.velInicial * Math.cos(Math.toRadians(this.angulo));
	}
	
	public void setVelInicial(double v) {
		this.velInicial = v;
	}
	
	public void setAngulo(double a) {
		this.angulo = a;
	}
	
	public Coordenada getCoordenadaFinal() {
		return cFinal;
	}
	
	

}
